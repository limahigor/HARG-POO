package com.hargclinical.harg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class HargApplication {

    @RequestMapping("/cadastrar-paciente")
    public String cadastrarPaciente(){
        return "cadastrar-paciente";
    }

    @RequestMapping("/cadastrar-medico")
    public String cadastrarMedico(){
        return "cadastrar-medico";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

	public static void main(String[] args) {
		SpringApplication.run(HargApplication.class, args);
	}

}
