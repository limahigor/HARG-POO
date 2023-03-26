package com.hargclinical.harg.resources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.entities_enums.Plano;
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

        return ResponseEntity.ok().body(medicos);
    }
    
    @GetMapping("/{id}")
    public ModelAndView paginaPaciente(ModelMap model, @PathVariable Long id){
        Medico medico = service.findById(id);

        ModelAndView viewPage = new ModelAndView("/html/templates/pagina-medico.html");
        viewPage.addObject("nome", medico.getNome());
        viewPage.addObject("crm", medico.getCrm());
        viewPage.addObject("especializacao", medico.getEspecializacao());

        return viewPage;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarMedico(@RequestBody String jsonData){
        ObjectMapper mapper = new ObjectMapper();
        Medico newMedico = null;

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

            System.out.println("Criando a instancia do medico\n==================================");

            char sexo = node.get("sexo").asText().charAt(0);
            String nome = node.get("nome").asText();
            String cpf = node.get("cpf").asText();
            String especializacao = node.get("especializacao").asText();
            String crm = node.get("crm").asText();
            LocalDate dataNascimento = LocalDate.parse(node.get("date").asText());

            Paciente newPaciente = new Paciente(nome, cpf, dataNascimento, sexo, Plano.PROFISSIONAL);
            servicoPaciente.insert(newPaciente);

            newMedico = new Medico(nome, cpf, dataNascimento, sexo, especializacao, crm);

            System.out.println("Inserindo medico no banco\n==================================");
            service.insert(newMedico);
            
            System.out.println("Pegando set de servicos\n==================================");
            Set<Services> medicoServicos = newMedico.getServicos();

            System.out.println("Adicionando os servicos\n==================================");
            medicoServicos.addAll(servicosJson);

            System.out.println("Atualizando medico no banco\n==================================");
            service.insert(newMedico);
            System.out.println("Finalizando...\n==================================");
            
        }catch(Exception e){
            System.out.println("ERROR!!");
            return ResponseEntity.badRequest().build();
        }
        

        // System.out.println(jsonData.nome);
        // jsonData = service.insert(jsonData);
        
        System.out.println("Finalizado...\n==================================");
        return ResponseEntity.ok().body(String.valueOf(newMedico.getId()));
    }

}