package com.hargclinical.harg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "html/index";
    }

    @GetMapping("/cadastrar-paciente")
    public String cadastrarPaciente() {
        return "html/cadastrar/cadastrar-paciente";
    }

    @GetMapping("/cadastrar-medico")
    public String cadastrarMedico() {
        return "html/cadastrar/cadastrar-medico";
    }

    @GetMapping("/buscar-medico")
    public String buscarMedico() {
        return "html/buscar/buscar-medico";
    }

    @GetMapping("/buscar-paciente")
    public String buscarPaciente() {
        return "html/buscar/buscar-paciente";
    }
}