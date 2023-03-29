package com.hargclinical.harg.resources;

import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.hargclinical.harg.entities.Comorbidades;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.utils.StringUtils;

@RestController
@RequestMapping("/paciente")
public class PacienteResource {

    @Autowired
    private PacienteService service;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> searchPacienteByName(@PathVariable Long id) {
        Paciente paciente = service.findById(id);

        return ResponseEntity.ok().body(paciente);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> searchPacienteByName(@RequestParam("name") String name) {
        List<Paciente> pacientes = new ArrayList<>();

        if (name.isEmpty()) {
            System.out.println("Parametro vazio");
            pacientes = service.findAll();
        } else {
            if (StringUtils.containsLettersAndDigits(name)) {

                return ResponseEntity.badRequest().body(null);

            } else if (StringUtils.containsOnlyDigits(name)) {

                pacientes = service.findByCpfContaining(name);

            } else if (StringUtils.containsOnlyLetters(name)) {

                pacientes = service.findByNomeContaining(name);

            }
        }
        if (pacientes.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok().body(pacientes);
    }

    @GetMapping("/{id}")
    public ModelAndView paginaPaciente(ModelMap model, @PathVariable Long id) {
        Paciente paciente = service.findById(id);

        ModelAndView viewPage = new ModelAndView("/html/templates/pagina-paciente.html");
        viewPage.addObject("id", paciente.getId());
        viewPage.addObject("nome", paciente.getNome());
        viewPage.addObject("cpf", paciente.getCpf());
        viewPage.addObject("idade", paciente.getIdade());
        viewPage.addObject("sexo", paciente.getSexo());
        
        Comorbidades comorbidade = paciente.getComorbidades();
        viewPage.addObject("fatorRisco", comorbidade.getFactorR());

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

    // @PostMapping("/cadastrar")
    // public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente
    // jsonData) {
    // System.out.println(jsonData.nome);
    // jsonData = service.insert(jsonData);
    // return ResponseEntity.ok().body(jsonData);
    // }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarMedico(@RequestBody String jsonData) {
        ObjectMapper mapper = new ObjectMapper();
        Paciente newPaciente = null;
        try {

            System.out.println("CADASTRANDO Paciente\n====================================");
            JsonNode node = mapper.readTree(jsonData);

            JsonNode comorbidadesNode = node.get("comorbidades");
            String dateString = node.get("date").asText();
            LocalDate date = LocalDate.parse(dateString);
            String cpf = node.get("cpf").asText();
            String nome = node.get("nome").asText();
            char sexo = node.get("sexo").asText().charAt(0);
            // int num_plano = node.get("plano").asInt();
            // precisa configurar o plano---
            Plano plano = Plano.valueOf(1);

            boolean tabagismo = false, obesidade = false, hipertensao = false, gestante = false, diabetes = false;
            for (JsonNode jNode : comorbidadesNode) {
                String idString = jNode.get("id").asText();
                boolean valor = jNode.get("value").asBoolean();
                if (idString.equals("tabagismo"))
                    tabagismo = valor;
                else if (idString.equals("obesidade"))
                    obesidade = valor;
                else if (idString.equals("hipertensao"))
                    hipertensao = valor;
                else if (idString.equals("gestante"))
                    gestante = valor;
                else if (idString.equals("diabetes"))
                    diabetes = valor;
            }

            System.out.println("Criando a instancia do paciente\n==================================");

            Comorbidades comorbidadesJson = new Comorbidades(tabagismo, obesidade, hipertensao, gestante, diabetes,
                    date);
            newPaciente = new Paciente(nome, cpf, date, sexo, plano);
            service.insert(newPaciente);

            newPaciente.setComorbidades(comorbidadesJson);
            comorbidadesJson.setPaciente(newPaciente);
            service.insert(newPaciente);

            System.out.println("Finalizando...\n==================================");

        } catch (Exception e) {
            System.out.println("ERROR!!");
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Finalizado...\n==================================");
        return ResponseEntity.ok().body(String.valueOf(newPaciente.getId()));
    }
}