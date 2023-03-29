package com.hargclinical.harg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.ServicesService;

import java.util.List;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaResource {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ServicesService servicesService;

    @GetMapping
    public ResponseEntity<List<Agenda>> findAll() {
        List<Agenda> agenda = agendaService.findAll();
        return ResponseEntity.ok().body(agenda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> findById(@PathVariable Long id) {
        Agenda agenda = agendaService.findById(id);
        return ResponseEntity.ok().body(agenda);
    }

    @GetMapping("/data/{dia}")
    public ResponseEntity<List<Appointment>> findByDay(@PathVariable int dia) {
        List<Appointment> consultas = agendaService.findByDay(dia);
        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<Agenda> findByMedico(@PathVariable long medico_id) {
        Agenda agenda = medicoService.findById(medico_id).getAgenda();
        return ResponseEntity.ok().body(agenda);
    }

    @GetMapping("/medico/{id}/data/{dia}")
    public ResponseEntity<List<Appointment>> findByMedicoDay(@PathVariable Long medico_id, @PathVariable int dia) {
        List<Appointment> consultas = medicoService.findById(medico_id).getAgenda().getDias().get(dia - 1).getConsultas();
        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/servico/{id}")
    public ResponseEntity<Agenda> findByService(@PathVariable long service_id) {
        Agenda agenda = servicesService.findById(service_id).getAgenda();
        return ResponseEntity.ok().body(agenda);
    }

    @GetMapping("/servico/{id}/data/{dia}")
    public ResponseEntity<List<Appointment>> findByServiceDay(@PathVariable Long service_id, @PathVariable int dia) {
        List<Appointment> consultas = servicesService.findById(service_id).getAgenda().getDias().get(dia - 1).getConsultas();
        return ResponseEntity.ok().body(consultas);
    }

    // @GetMapping("/agenda/{ano}/{mes}/{dia}")
    // public ResponseEntity<List<Appointment>> consultasDoDia(@PathVariable int ano, @PathVariable int mes, @PathVariable int dia) {
    //     LocalDate data = LocalDate.of(ano, mes, dia);
    //     List<Appointment> consultas = agendaService.getConsultasDoDia(data);
    //     if (consultas.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.ok().body(consultas);
    // }

    // @GetMapping("/agenda/{ano}/{mes}")
    // public ResponseEntity<Map<Integer, List<Appointment>>> consultasDoMes(@PathVariable int ano, @PathVariable int mes) {
    //     Map<Integer, List<Appointment>> consultasPorDia = agendaService.getConsultasDoMes(ano, mes);
    //     if (consultasPorDia.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.ok().body(consultasPorDia);
    // }

    // @GetMapping("/agenda/{ano}")
    // public ResponseEntity<Map<Integer, Map<Integer, List<Appointment>>>> consultasDoAno(@PathVariable int ano) {
    //     Map<Integer, Map<Integer, List<Appointment>>> consultasPorMesEDia = agendaService.buscarAgenda(ano);
    //     if (consultasPorMesEDia.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.ok().body(consultasPorMesEDia);
    // }

}