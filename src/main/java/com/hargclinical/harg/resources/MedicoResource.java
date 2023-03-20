package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.repositories.MedicoRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cadastrar-paciente-submit")
public class MedicoResource{

    @Autowired
    private MedicoRepository pessoaRepository;

    @PostMapping
    public String cadastrarPaciente(HttpServletRequest request) {
        Map<String, String[]> parametros = request.getParameterMap();
        List<String> par = new ArrayList<String>();
        
        for(String parametro : parametros.keySet()){


            par.add(Arrays.toString(parametros.get(parametro)));

            /*System.out.println(parametro + " = " + Arrays.toString(parametros.get(parametro)));*/
        }

        for(String parametro : par){
            System.out.println(parametro);
        }

        System.out.println("Nome: " + par.get(0) + ", CPF: " + par.get(1) + ", Idade: 22, " + "Sexo: " + par.get(3));

        /*Pessoa paciente1 = new Pessoa("Higor", "1231231234", 22, 'M');
        
        pessoaRepository.save(paciente1);*/

        return "Paciente cadastrado com sucesso!";
    }

}
