package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.ServProcedimento;
import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.services.ServicesService;

@RestController
@RequestMapping("/services")
public class ServicesResource {

    @Autowired
    private ServicesService service;

    @GetMapping("/procedimentos")
    public ResponseEntity<List<ServProcedimento>> getServicosProcedimentos() {
        List<Services> services = service.findAll();
        List <ServProcedimento> servProcedimentos = new ArrayList<>();

        for(Services serv : services){
            if(serv instanceof ServProcedimento){
                ServProcedimento aux = (ServProcedimento)serv;
                servProcedimentos.add(aux);
            }
        }

        return ResponseEntity.ok(servProcedimentos);
    }

    @GetMapping
    public ResponseEntity<List<Services>> getServicos() {
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
