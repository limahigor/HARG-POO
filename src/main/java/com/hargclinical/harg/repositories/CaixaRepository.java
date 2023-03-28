package com.hargclinical.harg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hargclinical.harg.entities.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
    
}
