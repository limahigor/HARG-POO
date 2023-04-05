package com.hargclinical.harg.services;

import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.repositories.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public Appointment findById(@PathVariable Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        return optionalAppointment.orElse(null);
    }

    public void save(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void deleteById(Long id){
        appointmentRepository.deleteById(id);
    }
}
