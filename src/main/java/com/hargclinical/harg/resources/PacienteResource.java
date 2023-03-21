package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.repositories.PacienteRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cadastrar-paciente-submit")
public class PacienteResource{

    @Autowired
    private PacienteRepository pessoaRepository;

    @PostMapping
    public String cadastrarPaciente(@RequestBody String jsonData) {
        
        System.out.println("Dados JSON recebidos: " + jsonData);

        return "Paciente cadastrado com sucesso!";
    }

}