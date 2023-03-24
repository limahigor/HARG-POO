package com.hargclinical.harg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/html/index.html");
        registry.addViewController("/cadastrar-paciente").setViewName("forward:/html/cadastrar/cadastrar-paciente.html");
        registry.addViewController("/cadastrar-medico").setViewName("forward:/html/cadastrar/cadastrar-medico.html");
        registry.addViewController("/marcar-consulta").setViewName("forward:/html/marcar/marcar-consulta.html");
        registry.addViewController("/marcar-exame").setViewName("forward:/html/marcar/marcar-exame.html");
        registry.addViewController("/marcar-procedimento").setViewName("forward:/html/marcar/marcar-procedimento.html");
        registry.addViewController("/buscar-paciente").setViewName("forward:/html/buscar/buscar-paciente.html");
        registry.addViewController("/buscar-medico").setViewName("forward:/html/buscar/buscar-medico.html");
        // registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");
        // registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");
        // registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");
    }

}