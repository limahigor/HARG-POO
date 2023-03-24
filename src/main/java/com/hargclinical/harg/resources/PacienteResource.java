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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.repositories.PacienteRepository;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.utils.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/paciente")
public class PacienteResource{

    @Autowired
    private PacienteService service;

    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> searchPacienteByName(@RequestParam("name") String name) {
        List<Paciente> pacientes = null;
        
        if(name.isEmpty()){
            System.out.println("Parametro vazio");
            pacientes = service.findAll();
        }else{
            if(StringUtils.containsLettersAndDigits(name)){

                return ResponseEntity.badRequest().body(null);

            }else if(StringUtils.containsOnlyDigits(name)){

                pacientes = service.findByCpfContaining(name);

            }else if(StringUtils.containsOnlyLetters(name)){

                pacientes = service.findByNomeContaining(name);

            }
        }

        return ResponseEntity.ok().body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente jsonData) {
        /*jsonData = service.insert(jsonData);*/
        System.out.println("Pequeno teste");
        return ResponseEntity.ok().body(jsonData);
    }

}