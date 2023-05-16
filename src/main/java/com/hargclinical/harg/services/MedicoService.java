package com.hargclinical.harg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.*;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.services.exceptions.IllegalArgument;
import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.repositories.MedicoRepository;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private ServicesService serviceService;

	@Autowired
	private AgendaService agendaService;

	public List<Medico> findAll() {
		return repository.findAll();
	}

	public Medico findById(Long id) {
		Optional<Medico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

    public List<Medico> findByNameContaining(String nome){
        return repository.findByNomeContaining(nome);
    }

    public List<Medico> findByCrmContaining(String nome) {
        return repository.findByCrmContaining(nome);
    }

	public Medico insert(Medico obj){
        return repository.save(obj);
    }

	public ModelAndView getModelAndView(Medico medico, ModelAndView viewPage) {
		viewPage.addObject("nome", medico.getNome());
		viewPage.addObject("crm", medico.getCrm());
		viewPage.addObject("especializacao", medico.getEspecializacao());

		return viewPage;
	}

	public Medico cadastrarMedicoService(String jsonData){
		String especializacao = pessoaService.extractString(jsonData, "especializacao");
		String crm = pessoaService.extractString(jsonData, "crm");

		Paciente newPaciente = getPacienteForMedico(jsonData);

		Medico newMedico = new Medico(newPaciente, especializacao, crm);

		List<Services> servicosJson = getProcedimentosFromJson(jsonData);
		newMedico.setServicos(servicosJson);

		createAgendaMedico(newMedico);

		try{
			insert(newMedico);
		}catch(Exception e) {
			throw new IllegalArgument("Erro ao cadastrar! Verifique os dados!");
		}

		return newMedico;
	}

	@NotNull
	private Paciente getPacienteForMedico(String jsonData) {
		Paciente newPaciente = pacienteService.cadastrarPacienteService(jsonData);
		Plano plano = Plano.valueOf(4);
		newPaciente.setPlanoSaude(plano);
		return newPaciente;
	}

	private void createAgendaMedico(Medico newMedico) {
		Agenda novaAgenda = new Agenda();
		novaAgenda.createAgenda();

		novaAgenda.setMedico(newMedico);
		newMedico.setAgenda(novaAgenda);
		agendaService.create(novaAgenda);
	}

	private List<Services> getProcedimentosFromJson(String jsonData) {
		ObjectMapper mapper = new ObjectMapper();

		try{
			JsonNode node = mapper.readTree(jsonData);

			JsonNode servicosNode = node.get("servicos");
			List<Services> servicosJson = new ArrayList<>();

			for(JsonNode jNode : servicosNode){
				Services servico = serviceService.findById(jNode.asLong());
				if(servico != null){
					servicosJson.add(servico);
				}
			}

			return servicosJson;
		}catch(Exception e) {
			throw new IllegalArgument("Erro ao cadastrar! Verifique os dados!");
		}
	}
}