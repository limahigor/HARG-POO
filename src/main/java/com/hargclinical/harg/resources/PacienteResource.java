package com.hargclinical.harg.resources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Comorbidades;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.Prescricao;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.utils.StringUtils;

@RestController
@RequestMapping("/paciente")
public class PacienteResource {

    @Autowired
    private PacienteService service;

    @GetMapping("/prescricao")
    public ResponseEntity<List<Prescricao>> searchPrescricaoByBoolean(@RequestParam("id") Long id) {
        Paciente paciente = service.findById(id);
        List<Prescricao> prescricaoPaciente = paciente.getPrescricoes();
        List<Prescricao> prescricao = new ArrayList<>();

        for (Prescricao presc : prescricaoPaciente) {
            if (!presc.isOrcamento_gerado()) {
                prescricao.add(presc);
            }
        }

        return ResponseEntity.ok().body(prescricao);
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> searchAppointmentByBoolean(@RequestParam("id") Long id) {
        Paciente paciente = service.findById(id);
        List<Appointment> consultaPaciente = paciente.getAppointments();
        List<Appointment> consultas = new ArrayList<>();

        for (Appointment consul : consultaPaciente) {
            if (!consul.isOrcamentoGerado()) {
                consultas.add(consul);
            }
        }

        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> searchPacienteByName(@PathVariable Long id) {
        Paciente paciente = service.findById(id);

        return ResponseEntity.ok().body(paciente);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> searchPacienteByName(@RequestParam("name") String name) {
        List<Paciente> pacientes = new ArrayList<>();

        if (name.isEmpty()) {
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

        return service.getModelAndView(paciente, viewPage);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarMedico(@RequestBody String jsonData) {
        final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        ObjectMapper mapper = new ObjectMapper();
        Paciente newPaciente = null;
        try {
            logger.log(Level.INFO, "CADASTRANDO Paciente\n====================================");
            JsonNode node = mapper.readTree(jsonData);

            JsonNode comorbidadesNode = node.get("comorbidades");
            String dateString = node.get("date").asText();
            LocalDate date = LocalDate.parse(dateString);
            String cpf = node.get("cpf").asText();
            String nome = node.get("nome").asText();
            char sexo = node.get("sexo").asText().charAt(0);
            int planoId = node.get("plano").asInt();

            Plano plano = Plano.valueOf(planoId);

            boolean tabagismo = false;
            boolean hipertensao = false;
            boolean gestante = false;
            boolean diabetes = false;
            boolean obesidade = false;

            for (JsonNode jNode : comorbidadesNode) {
                String idString = jNode.get("id").asText();
                boolean valor = jNode.get("value").asBoolean();
                switch (idString) {
                    case "tabagismo" -> tabagismo = valor;
                    case "obesidade" -> obesidade = valor;
                    case "hipertensao" -> hipertensao = valor;
                    case "gestante" -> gestante = valor;
                    case "diabetes" -> diabetes = valor;
                    default -> {
                        return ResponseEntity.badRequest().build();
                    }
                }
            }

            logger.log(Level.INFO, "Criando a instancia do paciente\n==================================");

            Comorbidades comorbidadesJson = new Comorbidades(tabagismo, obesidade, hipertensao, gestante, diabetes, date);
            newPaciente = new Paciente(nome, cpf, date, sexo, plano);
            service.insert(newPaciente);

            newPaciente.setComorbidades(comorbidadesJson);
            comorbidadesJson.setPaciente(newPaciente);
            service.insert(newPaciente);

            logger.log(Level.INFO, "Finalizando...\n==================================");

        } catch (Exception e) {


            logger.log(Level.INFO, "ERROR!!");
            return ResponseEntity.badRequest().build();
        }

        logger.log(Level.INFO, "Finalizado...\n==================================");
        return ResponseEntity.ok().body(String.valueOf(newPaciente.getId()));
    }
}