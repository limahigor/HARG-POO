package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.services.ServicesService;

@RestController
@RequestMapping("/services")
public class ServicesResource {

    @Autowired
    private ServicesService service;

    @GetMapping
    public ResponseEntity<List<Services>> listarServicos() {
        List<Services> services = service.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<Services> findById(@PathVariable Long id) {
		Services obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    /*@PostMapping
    public ResponseEntity<Services> adicionarServico(Services servico){
        Services novoServico = service.save(servico);
        return ResponseEntity.ok(novoServico);
    }*/

    
}
