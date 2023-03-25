package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name="agenda")
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<Appointment> consultas;

    public Agenda() {
        this.consultas = new ArrayList<>();
    }
    
    public void agendarConsulta(LocalDate data, LocalTime horario, Paciente paciente, Medico medico, Services services) {
        Appointment consulta = new Appointment(medico, paciente, services, data, horario);
        consultas.add(consulta);
    }

}