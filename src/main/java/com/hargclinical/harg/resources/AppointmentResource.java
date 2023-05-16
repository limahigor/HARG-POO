package com.hargclinical.harg.resources;

import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/appointments")
public class AppointmentResource {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll() {
        List<Appointment> appointments = appointmentService.findAll();
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable Long id) {
        Appointment appointment = appointmentService.findById(id);

        if(appointment == null)
            return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(appointment);
    }

    @GetMapping(value = "/medico")
    public ResponseEntity<List<Appointment>> findByProced(@RequestParam("id") Long id, @RequestParam("tipo") String servico) {
        if(servico != null) {
            List<Appointment> consultasRetorno = new ArrayList<>();

            Medico medico = medicoService.findById(id);

            List<Appointment> consultasMedico = medico.getAppointments();
            
            consultasRetorno = appointmentService.returnConsultas(consultasMedico, servico);

            if(consultasRetorno == null) return ResponseEntity.badRequest().body(null);
            else return ResponseEntity.ok().body(consultasRetorno);

        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/paciente")
    public ResponseEntity<List<Appointment>> findByService(@RequestParam("id") Long id, @RequestParam("tipo") String servico) {
        if(servico != null) {
            List<Appointment> consultasRetorno = new ArrayList<>();

            Paciente paciente = pacienteService.findById(id);

            List<Appointment> consultasPaciente = paciente.getProntuario().getAppointments();

            consultasRetorno = appointmentService.returnConsultas(consultasPaciente, servico);
            
            if(consultasRetorno == null) return ResponseEntity.badRequest().body(null);
            else return ResponseEntity.ok().body(consultasRetorno);

        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = "/agendar")
    public ResponseEntity<String> agendarConsulta(@RequestBody String jsonData) {
        Appointment newAppointment = appointmentService.agendamento(jsonData);
        return ResponseEntity.ok().body(String.valueOf(newAppointment.getId()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}