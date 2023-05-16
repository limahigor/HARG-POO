package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import com.hargclinical.harg.entities.Comorbidades;
import com.hargclinical.harg.services.exceptions.IllegalArgument;
import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.repositories.PacienteRepository;
import org.springframework.web.servlet.ModelAndView;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PessoaService pessoaService;

    public List<Paciente> findAll() {
        return repository.findAll();
    }

    public Paciente findById(Long id) {
        Optional<Paciente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Paciente> findByCpfContaining(String name) {
        return repository.findByCpfContaining(name);
    }

    public List<Paciente> findByNomeContaining(String name) {
        return repository.findByNomeContaining(name);
    }
    public Paciente insert(Paciente obj){
        return repository.save(obj);
    }

    public ModelAndView getModelAndView(Paciente paciente, ModelAndView viewPage) {
        viewPage.addObject("id", paciente.getId());
        viewPage.addObject("nome", paciente.getNome());
        viewPage.addObject("cpf", paciente.getCpf());
        viewPage.addObject("idade", paciente.getIdade());
        viewPage.addObject("sexo", paciente.getSexo());

        String plano = "plano";
        switch (paciente.getPlanoSaude().getCode()) {
            case 1 -> viewPage.addObject(plano, "BRONZE");
            case 2 -> viewPage.addObject(plano, "PRATA");
            case 3 -> viewPage.addObject(plano, "OURO");
            case 4 -> viewPage.addObject(plano, "PLATINA");
            default -> viewPage.addObject(plano, "NENHUM");
        }

        Comorbidades comorbidade = paciente.getProntuario().getComorbidades();
        viewPage.addObject("fatorRisco", comorbidade.getFactorRisco());

        if (comorbidade.isGestante())
            viewPage.addObject("gestante", "SIM");
        else
            viewPage.addObject("gestante", "NÃO");

        if (comorbidade.isDiabetes())
            viewPage.addObject("diabetico", "SIM");
        else
            viewPage.addObject("diabetico", "NÃO");

        if (comorbidade.isHipertensao())
            viewPage.addObject("hipertenso", "SIM");
        else
            viewPage.addObject("hipertenso", "NÃO");

        if (comorbidade.isIdade())
            viewPage.addObject("idoso", "SIM");
        else
            viewPage.addObject("idoso", "NÃO");

        if (comorbidade.isObesidade())
            viewPage.addObject("obeso", "SIM");
        else
            viewPage.addObject("obeso", "NÃO");

        if (comorbidade.isTabagismo())
            viewPage.addObject("fumante", "SIM");
        else
            viewPage.addObject("fumante", "NÃO");

        return viewPage;
    }

    public Paciente cadastrarPacienteService(String jsonData){
        System.out.println("TESTE AQUI PACIENTE SERVICE");
        Paciente newPaciente = pessoaService.createPersonFromJson(jsonData);
        System.out.println("TESTE AQUI PACIENTE SERVICE");

        try{
            insert(newPaciente);
        }catch(DataIntegrityViolationException e) {
            if (e.getMessage().contains("constraint") && e.getMessage().contains("pacientes.UK_1mj2svx930q0tkx1d18qa9rtf")) {
                throw new IllegalArgument("CPF já cadastrado!");
            }
            throw e;
        }

        return newPaciente;
    }
}