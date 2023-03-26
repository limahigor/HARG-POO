package com.hargclinical.harg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hargclinical.harg.entities.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    
}