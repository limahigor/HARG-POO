package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.List;

import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
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

import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.Prescricao;
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
        List<Prescricao> prescricaoPaciente = paciente.getProntuario().getPrescricoes();
        List<Prescricao> prescricao = new ArrayList<>();

        for (Prescricao presc : prescricaoPaciente) {
            if (!presc.isOrcamentoGerado()) {
                prescricao.add(presc);
            }
        }

        return ResponseEntity.ok().body(prescricao);
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> searchAppointmentByBoolean(@RequestParam("id") Long id) {
        Paciente paciente = service.findById(id);
        List<Appointment> consultaPaciente = paciente.getProntuario().getAppointments();
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
        try {
            Paciente paciente = service.findById(id);
            ModelAndView viewPage = new ModelAndView("/html/templates/pagina-paciente.html");

            return service.getModelAndView(paciente, viewPage);
        }catch(ResourceNotFoundException e) {
            return new ModelAndView("/html/templates/404.html");
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody String jsonData) {
        Paciente newPaciente = service.cadastrarPacienteService(jsonData);

        return ResponseEntity.ok().body(newPaciente);
    }
}