package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.repositories.PacienteRepository;
import com.hargclinical.harg.services.PacienteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cadastrar-paciente-submit")
public class PacienteResource{

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente jsonData) {
        jsonData = service.insert(jsonData);
        return ResponseEntity.ok().body(jsonData);

        
    
    }

}