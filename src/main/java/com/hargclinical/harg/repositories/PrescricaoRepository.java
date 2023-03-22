package com.hargclinical.harg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hargclinical.harg.entities.Prescricao;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {

}
