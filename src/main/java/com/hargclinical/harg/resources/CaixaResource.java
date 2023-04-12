package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Caixa;
import com.hargclinical.harg.services.CaixaService;

@RestController
@RequestMapping(value = "/caixa")
public class CaixaResource {
    
    @Autowired
    private CaixaService service;

    @PostMapping("/abrir")
    public ResponseEntity<Object> abrirCaixa(){
        Caixa caixa = service.abrirCaixa();

        return ResponseEntity.ok().body(caixa);
    }

    @PostMapping("/fechar")
    public ResponseEntity<Object> fecharCaixa(){
        Caixa caixa = service.fecharCaixa();

        return ResponseEntity.ok().body(caixa);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<Caixa>> historico(){
        List<Caixa> caixas = service.findAll();
        return ResponseEntity.ok().body(caixas); 
    }

    @GetMapping("/status")
    public ResponseEntity<Caixa> status(){
        List<Caixa> caixas = service.findAll();
        Caixa caixa = caixas.get(caixas.size()-1);
        if(caixa.getAberto())return ResponseEntity.ok().body(caixa);
        return ResponseEntity.ok().body(caixa);
    }

}
