package com.hargclinical.harg.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.repositories.AppointmentRepository;
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.services.ServicesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value="/appointments")
public class AppointmentResource {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable Long id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        return ResponseEntity.ok().body(appointment);
    }

    @PostMapping(value = "/agendar")
    public ResponseEntity<String> agendarConsulta(@RequestBody String jsonData) {
        ObjectMapper mapper = new ObjectMapper();
        Appointment newAppointment = null;

        try{
            JsonNode node = mapper.readTree(jsonData);

            String cpf = node.get("cpf").asText();
            String data = node.get("data").asText();
            LocalDate date = LocalDate.parse(data);
            String hora = node.get("hora").asText();
            LocalTime horario = LocalTime.parse(hora);
            Long service_id = node.get("procedimento").asLong();
            Long medico_id = node.get("medico").asLong();
            
            Medico medico = medicoService.findById(medico_id);
            Services service = servicesService.findById(service_id);
            Paciente paciente = pacienteService.findByCpfContaining(cpf).get(0);

            newAppointment = new Appointment(medico, paciente, service, date, horario);

            Agenda geralAgenda = agendaService.findById(1L);

            Agenda medicoAgenda = medico.getAgenda();
            Agenda serviceAgenda = service.getAgenda();

            medicoAgenda.agendarConsulta(newAppointment);
            serviceAgenda.agendarConsulta(newAppointment);
            geralAgenda.agendarConsulta(newAppointment);

            agendaService.create(serviceAgenda);
            agendaService.create(geralAgenda);
            agendaService.create(medicoAgenda);

        } catch (Exception e) {
            System.out.println("ERROR!!");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(String.valueOf(newAppointment.getId()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}