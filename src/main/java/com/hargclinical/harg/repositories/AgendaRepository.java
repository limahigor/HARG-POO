package com.hargclinical.harg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hargclinical.harg.entities.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}