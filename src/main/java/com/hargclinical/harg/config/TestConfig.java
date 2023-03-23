/*package com.hargclinical.harg.config;

import com.hargclinical.harg.repositories.MedicoRepository;
import com.hargclinical.harg.repositories.ServicesRepository;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.ServConsulta;
import com.hargclinical.harg.entities.ServExame;
import com.hargclinical.harg.entities.ServProcedimento;
import com.hargclinical.harg.entities.Services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestConfig implements CommandLineRunner{

    private static final String Services = null;

    @Autowired
    private ServicesRepository serviceRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void run(String... args) throws Exception {

        try{

            Services serv1 = new ServConsulta("Cardiologista", "Cardiologia", 200, 30);
            Services serv2 = new ServProcedimento("Botox", "Dermatologista", 200, 30, "Nenhuma");
            Services serv3 = new ServExame("Hemograma Completo", "Endocrinologia", 200, "Jejum 8 horas");
            Services serv4 = new ServProcedimento("Ecocardiograma", "Cardiologia", 200, 30, "Nenhuma");
            serviceRepository.saveAll(Arrays.asList(serv1, serv2, serv3, serv4));

            Medico medico1 = new Medico("Manoel Gomes", "1654116515616", 36, 'M', "Dermatologista", "12312412");
            Medico medico2 = new Medico("Higor Gomes", "1654116515616", 36, 'M', "Dermatologista", "12312412");
            Medico medico3 = new Medico("Guilherme Gomes", "1654116515616", 36, 'M', "Cardiologia", "12312412");
            Medico medico4 = new Medico("Anderson Silva", "1654116515616", 36, 'M', "Endocrinologia", "12312412");
            medicoRepository.saveAll(Arrays.asList(medico1, medico2, medico3,medico4));
            
            medico1.getServicos().add(serv2);
            medico2.getServicos().add(serv2);
            medico3.getServicos().add(serv1);
            medico3.getServicos().add(serv4);
            medico4.getServicos().add(serv3);
            medicoRepository.saveAll(Arrays.asList(medico1, medico2, medico3,medico4));
            
        }catch(Exception e){
            System.out.println("TESTE ERROR");
            System.out.println(e.getMessage());
        }

    }
    
}*/