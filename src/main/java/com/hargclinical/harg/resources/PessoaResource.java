package com.hargclinical.harg.resources;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cadastrar-paciente-submit")
public class PessoaResource{
    
    @PostMapping
    public String cadastrarPaciente(HttpServletRequest request) {
        Map<String, String[]> parametros = request.getParameterMap();
        
        for (String parametro : parametros.keySet()) {
            System.out.println(parametro + " = " + Arrays.toString(parametros.get(parametro)));
        }

        return "Paciente cadastrado com sucesso!";
    }

}
