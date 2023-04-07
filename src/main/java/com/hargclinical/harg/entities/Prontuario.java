package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="prontuarios")
public class Prontuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    private final List<Appointment> consultas = new ArrayList<>();
    private final List<Prescricao> prescricoes = new ArrayList<>();
    
    public Prontuario() {
    }

    public Prontuario(Paciente paciente){
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return null;
    }
}

