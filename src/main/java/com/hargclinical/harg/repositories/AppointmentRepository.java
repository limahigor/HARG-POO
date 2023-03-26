package com.hargclinical.harg.repositories;

import com.hargclinical.harg.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
