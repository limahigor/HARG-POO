package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.repositories.PacienteRepository;
import com.hargclinical.harg.services.PacienteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/paciente")
public class PacienteResource{

    @Autowired
    private PacienteService service;

    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> findAll() {
		List<Paciente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

    @PostMapping("/cadastrar")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente jsonData) {
        /*jsonData = service.insert(jsonData);*/
        System.out.println("Pequeno teste");
        return ResponseEntity.ok().body(jsonData);
    }

}