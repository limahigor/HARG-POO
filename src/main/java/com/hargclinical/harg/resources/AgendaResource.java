package com.hargclinical.harg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Dias;
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.ServicesService;

import java.util.ArrayList;
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

    @GetMapping("/geral")
    public ResponseEntity<List<Appointment>> findByGeneral() {
        Agenda agenda = agendaService.findById(1L);

        List<Appointment> consultas = new ArrayList<>();

        for(Dias dia : agenda.getDias()){
            if(!dia.getConsultas().isEmpty())
                consultas.addAll(dia.getConsultas());
        }

        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/data")
    public ResponseEntity<List<Appointment>> findByDay(@RequestParam("dia") int dia) {
        List<Appointment> consultas = agendaService.findByDay(dia);
        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/medico")
    public ResponseEntity<List<Appointment>> findByMedico(@RequestParam("id") Long medico_id) {
        Agenda agenda = medicoService.findById(medico_id).getAgenda();

        List<Appointment> consultas = new ArrayList<>();

        for(Dias dia : agenda.getDias()){
            if(!dia.getConsultas().isEmpty()){
                consultas.addAll(dia.getConsultas());
            }
        }

        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/medico/data")
    public ResponseEntity<List<Appointment>> findByMedicoDay(@RequestParam("id") Long medico_id, @RequestParam("dia") int dia) {
        List<Appointment> consultas = medicoService.findById(medico_id).getAgenda().getDias().get(dia - 1).getConsultas();
        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/servico")
    public ResponseEntity<List<Appointment>> findByService(@RequestParam("id") Long service_id) {
        Agenda agenda = servicesService.findById(service_id).getAgenda();

        List<Appointment> consultas = new ArrayList<>();

        for(Dias dia : agenda.getDias()){
            if(!dia.getConsultas().isEmpty()){
                consultas.addAll(dia.getConsultas());
            }
        }

        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping("/servico/data")
    public ResponseEntity<List<Appointment>> findByServiceDay(@RequestParam("id") Long service_id, @RequestParam("dia") int dia) {
        List<Appointment> consultas = servicesService.findById(service_id).getAgenda().getDias().get(dia - 1).getConsultas();
        return ResponseEntity.ok().body(consultas);
    }

}