package com.hargclinical.harg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.services.AgendaService;

import java.util.List;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaResource {

    @Autowired
    private AgendaService agendaService;

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
    
}