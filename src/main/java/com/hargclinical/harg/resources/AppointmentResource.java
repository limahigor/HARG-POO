package com.hargclinical.harg.resources;

import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/appointments")
public class AppointmentResource {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable Long id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        return ResponseEntity.ok().body(appointment);
    }

    @PostMapping
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
        appointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok().body(appointment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
