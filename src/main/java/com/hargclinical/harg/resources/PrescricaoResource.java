package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Prescricao;
import com.hargclinical.harg.repositories.PrescricaoRepository;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoResource {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @GetMapping
    public ResponseEntity<List<Prescricao>> listarPrescricoes() {
        List<Prescricao> prescricoes = prescricaoRepository.findAll();
        return ResponseEntity.ok(prescricoes);
    }

    @PostMapping
    public ResponseEntity<Prescricao> adicionarPrescricao(@RequestBody Prescricao prescricao) {
        Prescricao prescricaoSalva = prescricaoRepository.save(prescricao);
        return ResponseEntity.ok(prescricaoSalva);
    }
    
}
