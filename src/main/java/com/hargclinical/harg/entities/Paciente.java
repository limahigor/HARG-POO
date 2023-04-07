package com.hargclinical.harg.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.serializables.PacienteSerializer;

import jakarta.persistence.*;

@Entity
@Table(name="pacientes")
@PrimaryKeyJoinColumn(name = "pacienteId")
@JsonSerialize(using = PacienteSerializer.class)
public class Paciente extends Pessoa{

    private Plano planoSaude;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private final List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private final List<Prescricao> prescricoes = new ArrayList<>();
    
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Comorbidades comorbidades;
    
    public Paciente(){
        super();
    }
    public Paciente(String nome, String cpf, LocalDate date, char sexo, Plano planoSaude){
        super(nome, cpf, date, sexo); 
        this.planoSaude = planoSaude;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public Plano getPlanoSaude() {
        return planoSaude;
    }

    public Comorbidades getComorbidades() {
        return comorbidades;
    }

    public void setComorbidades(Comorbidades comorbidades) {
        this.comorbidades = comorbidades;
    }

    public int getFactorRisco(){
       return comorbidades.getFactorR();
    }
    
    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }
}
