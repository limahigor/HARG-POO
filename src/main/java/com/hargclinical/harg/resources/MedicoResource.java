package com.hargclinical.harg.resources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hargclinical.harg.services.exceptions.IllegalArgument;
import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
import com.hargclinical.harg.utils.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Comorbidades;
import com.hargclinical.harg.entities.Dias;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.services.ServicesService;
import com.hargclinical.harg.utils.StringUtils;

@RestController
@RequestMapping(value = "/medico")
public class MedicoResource{

    @Autowired
    private MedicoService service;

    @Autowired
    private ServicesService serviceService;

    @Autowired
    private PacienteService servicoPaciente;

    @Autowired
    private AgendaService agendaService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Medico>> searchMedicoByName(@RequestParam("name") String name) {
        List<Medico> medicos = null;
        
        if(name.isEmpty()){
            System.out.println("Parametro vazio");
            medicos = service.findAll();
        }else{
            if(StringUtils.containsLettersAndDigits(name)){

                return ResponseEntity.badRequest().body(null);

            }else if(StringUtils.containsOnlyDigits(name)){

                medicos = service.findByCrmContaining(name);

            }else if(StringUtils.containsOnlyLetters(name)){

                medicos = service.findByNameContaining(name);

            }
        }

        assert medicos != null;
        if(medicos.isEmpty())return ResponseEntity.badRequest().body(null);
        
        return ResponseEntity.ok().body(medicos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Medico> searchMedicoById(@PathVariable Long id){

        Medico medico = service.findById(id);

        return ResponseEntity.ok().body(medico);
    }
    
    @GetMapping("/{id}")
    public ModelAndView paginaPaciente(ModelMap model, @PathVariable Long id){
        try {
            Medico medico = service.findById(id);
            ModelAndView viewPage = new ModelAndView("/html/templates/pagina-medico.html");
            return service.getModelAndView(medico, viewPage);
        }catch(ResourceNotFoundException e) {
            ModelAndView viewPage = new ModelAndView("/html/templates/404.html");
            return viewPage;
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody String jsonData){
        ObjectMapper mapper = new ObjectMapper();
        Medico newMedico = null;
        Paciente newPaciente = null;

        try{
            System.out.println("CADASTRANDO MEDICO\n====================================");
            JsonNode node = mapper.readTree(jsonData);

            JsonNode servicosNode = node.get("servicos");
            List<Services> servicosJson = new ArrayList<>();

            for(JsonNode jNode : servicosNode){
                System.out.println("Pegando Servicos\n==================================");
                Services servico = serviceService.findById(jNode.asLong());
                if(servico != null){
                    System.out.println("Servico: " + servico.nome);
                    servicosJson.add(servico);
                }
            }

            String especializacao = node.get("especializacao").asText();

            if(servicosJson.isEmpty()){
                throw new IllegalArgument("Médico sem serviços cadastrados.");
            }
            
            for (Services servico : servicosJson) {

                if (servicosJson.isEmpty()) {
                    throw new IllegalArgument("Não há serviços cadastrados para essa especialidade.");
                }

                else if (!servico.getEspecialidade().equals(especializacao)) {
                    throw new IllegalArgument("Especialidade do serviço é diferente da especialidade do médico.");
                }

            }

            JsonNode comorbidadesNode = node.get("comorbidades");
            String dateString = node.get("date").asText();
            LocalDate date = LocalDate.parse(dateString);
            Plano plano = Plano.valueOf(4);

            boolean tabagismo=false, obesidade=false, hipertensao=false, gestante=false, diabetes=false;
            for(JsonNode jNode : comorbidadesNode){
                String idString = jNode.get("id").asText();
                boolean valor = jNode.get("value").asBoolean();
                switch (idString) {
                    case "tabagismo" -> tabagismo = valor;
                    case "obesidade" -> obesidade = valor;
                    case "hipertensao" -> hipertensao = valor;
                    case "gestante" -> gestante = valor;
                    case "diabetes" -> diabetes = valor;
                    default -> throw new IllegalArgument("Comorbidade inválida!!");
                }
            }

            System.out.println("Criando a instancia do medico\n==================================");

            char sexo = node.get("sexo").asText().charAt(0);
            String nome = node.get("nome").asText();
            String cpf = node.get("cpf").asText();
            especializacao = node.get("especializacao").asText();
            String crm = node.get("crm").asText();
            LocalDate dataNascimento = LocalDate.parse(node.get("date").asText());

            if(sexo == 'M' && gestante){
                throw new IllegalArgument("Falha ao cadastrar!");
            }

            if(!StringUtils.containsOnlyDigits(cpf)){
                throw new IllegalArgument("CPF Inválido! Somente dígitos!");
            }

            if(!CPF.isCPFValid(cpf)){
                throw new IllegalArgument("CPF Inválido!");
            }

            Comorbidades comorbidadesJson = new Comorbidades(tabagismo, obesidade, hipertensao, gestante, diabetes, date);
            
            newPaciente = new Paciente(nome, cpf, dataNascimento, sexo, plano);

            newPaciente.setProntuario();
            newPaciente.getProntuario().setComorbidades(comorbidadesJson);
            comorbidadesJson.setProntuario(newPaciente.getProntuario());
            servicoPaciente.insert(newPaciente);

            newMedico = new Medico(nome, cpf, dataNascimento, sexo, especializacao, crm);

            Agenda novaAgenda = new Agenda();
            List<Dias> dias = new ArrayList<>();
            novaAgenda.setListaDias(dias, novaAgenda);
            novaAgenda.setDias(dias);
            novaAgenda.setMedico(newMedico);
            newMedico.setAgenda(novaAgenda);
            agendaService.create(novaAgenda);

            System.out.println("Inserindo medico no banco\n==================================");
            service.insert(newMedico);
            
            System.out.println("Pegando set de servicos\n==================================");
            Set<Services> medicoServicos = newMedico.getServicos();

            System.out.println("Adicionando os servicos\n==================================");
            medicoServicos.addAll(servicosJson);

            System.out.println("Atualizando medico no banco\n==================================");
            service.insert(newMedico);
            System.out.println("Finalizando...\n==================================");
            
        }catch(DataIntegrityViolationException e) {
            if (e.getMessage().contains("constraint") && e.getMessage().contains("pacientes.UK_1mj2svx930q0tkx1d18qa9rtf")) {
                throw new IllegalArgument("CPF já cadastrado!");
            }
            throw e;
        }catch(IllegalArgument e){
            throw new IllegalArgument(e.getMessage());
        }catch(Exception e){
            throw new IllegalArgument("Falha ao cadastrar!");
        }
        
        System.out.println("Finalizado...\n==================================");
        return ResponseEntity.ok().body(newMedico);
    }

}