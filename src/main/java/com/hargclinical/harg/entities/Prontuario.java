package com.hargclinical.harg.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="prontuarios")
public class Prontuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private final List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private final List<Prescricao> prescricoes = new ArrayList<>();

    @OneToOne(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private Comorbidades comorbidades;
    
    public Prontuario() {
    }

    public Prontuario(Paciente paciente){
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setComorbidades(Comorbidades comorbidades) {
        this.comorbidades = comorbidades;
    }

    public Comorbidades getComorbidades() {
        return comorbidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prontuario that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

