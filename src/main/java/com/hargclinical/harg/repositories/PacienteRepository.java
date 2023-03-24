package com.hargclinical.harg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hargclinical.harg.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNomeContaining(String nome);
    List<Paciente> findByCpfContaining(String nome);
}
