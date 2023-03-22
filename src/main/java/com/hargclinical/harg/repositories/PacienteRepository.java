package com.hargclinical.harg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hargclinical.harg.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}
