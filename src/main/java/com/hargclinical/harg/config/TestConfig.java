// package com.hargclinical.harg.config;

// import com.hargclinical.harg.entities.Paciente;
// import com.hargclinical.harg.entities.Pessoa;
// import com.hargclinical.harg.repositories.PacienteRepository;

// import java.util.Arrays;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;



// @Configuration
// @Profile("test")
// public class TestConfig implements CommandLineRunner{

//     @Autowired
//     private PacienteRepository pacienteRepository;

//     @Override
//     public void run(String... args) throws Exception {
//         Pessoa p1 = new Paciente("Anderson", "70850528488", 21, 'M', "gine");
//         Pessoa p2 = new Paciente("Higor", "51545454454", 22, 'M', "pros");
        
//         pacienteRepository.saveAll(Arrays.asList(p1, p2));
//     }
    
// }
