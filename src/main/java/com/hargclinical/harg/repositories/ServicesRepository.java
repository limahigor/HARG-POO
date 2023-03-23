package com.hargclinical.harg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hargclinical.harg.entities.Services;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

    

}