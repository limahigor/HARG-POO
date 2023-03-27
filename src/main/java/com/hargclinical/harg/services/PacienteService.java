package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.repositories.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<Paciente> findAll() {
        return repository.findAll();
    }

    public Paciente findById(Long id) {
        Optional<Paciente> obj = repository.findById(id);
        return obj.get();
    }

    public List<Paciente> findByCpfContaining(String name) {
        return repository.findByCpfContaining(name);
    }

    public List<Paciente> findByNomeContaining(String name) {
        return repository.findByNomeContaining(name);
    }
    public Paciente insert(Paciente obj){
        return repository.save(obj);

    }
    
}