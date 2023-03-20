package com.hargclinical.harg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cadastrar-paciente").setViewName("forward:/html/cadastrar-paciente.html");
        registry.addViewController("/cadastrar-medico").setViewName("forward:/html/cadastrar-medico.html");
        registry.addViewController("/").setViewName("forward:/html/index.html");
        /*registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");
        registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");
        registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");
        registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");
        registry.addViewController("/cadastrar-medico").setViewName("forward:/cadastrar-medico.html");*/
    }

}