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

    @GetMapping("/caixa/abrir")
    public void abrirCaixa(){
        service.abrirCaixa();
    }

    @GetMapping("/caixa/fechar")
    public void fecharCaixa(){
        service.fecharCaixa();
    }

    @GetMapping("/caixa/historico")
    public ResponseEntity<List<Caixa>> historico(){
        List<Caixa> caixas = service.findAll();
        return ResponseEntity.ok().body(caixas); 
    }
}
