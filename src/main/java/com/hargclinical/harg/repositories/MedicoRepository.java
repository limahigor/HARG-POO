package com.hargclinical.harg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hargclinical.harg.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByNomeContaining(String nome);
    List<Medico> findByCrmContaining(String nome);
}
