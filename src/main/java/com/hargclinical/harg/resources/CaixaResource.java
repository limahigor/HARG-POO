package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Caixa;
import com.hargclinical.harg.services.CaixaService;

@RestController
@RequestMapping(value = "/caixa")
public class CaixaResource {
    
    @Autowired
    private CaixaService service;

    @GetMapping("/abrir")
    public void abrirCaixa(){
        service.abrirCaixa();
    }

    @GetMapping("/fechar")
    public void fecharCaixa(){
        service.fecharCaixa();
    }

    @GetMapping("/historico")
    public ResponseEntity<List<Caixa>> historico(){
        List<Caixa> caixas = service.findAll();
        return ResponseEntity.ok().body(caixas); 
    }

    @GetMapping("/status")
    public String status(){
        List<Caixa> caixas = service.findAll();
        Caixa caixa = caixas.get(caixas.size()-1);
        String stts = "fechado";
        if(caixa.getAberto())stts = "aberto";
        return stts;
    }

}
