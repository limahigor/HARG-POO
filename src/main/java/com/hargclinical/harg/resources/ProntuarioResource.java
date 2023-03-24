package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Prontuario;
import com.hargclinical.harg.repositories.ProntuarioRepository;

@RestController
@RequestMapping(value = "/prontuarios")
public class ProntuarioResource {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @GetMapping
    public ResponseEntity<List<Prontuario>> getProntuarios() {
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        return ResponseEntity.ok().body(prontuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Prontuario> findById(@PathVariable Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id).orElse(null);
        if (prontuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(prontuario);
    }

    @PostMapping
    public ResponseEntity<Prontuario> saveProntuario(@RequestBody Prontuario prontuario) {
        prontuario = prontuarioRepository.save(prontuario);
        return ResponseEntity.ok().body(prontuario);
    }
}
