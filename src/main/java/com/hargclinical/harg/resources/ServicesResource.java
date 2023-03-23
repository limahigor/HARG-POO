package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.repositories.ServicesRepository;

@RestController
@RequestMapping("/services")
public class ServicesResource {

    @Autowired
    private ServicesRepository servicesRepository;

    @GetMapping
    public ResponseEntity<List<Services>> listarServicos() {
        List<Services> services = servicesRepository.findAll();
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<Services> adicionarServico(Services servico){
        Services novoServico = servicesRepository.save(servico);
        return ResponseEntity.ok(novoServico);
    }

    
}
