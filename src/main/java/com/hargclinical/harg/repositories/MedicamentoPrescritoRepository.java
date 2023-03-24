package com.hargclinical.harg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hargclinical.harg.entities.MedicamentoPrescrito;
import com.hargclinical.harg.entities.Prescricao;

public interface MedicamentoPrescritoRepository extends JpaRepository<MedicamentoPrescrito, Long> {

}
