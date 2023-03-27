package com.hargclinical.harg.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.serializables.PacienteSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="pacientes")
@JsonSerialize(using = PacienteSerializer.class)
public class Paciente extends Pessoa{

    public Plano plano_saude;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comorbidades")
    private Comorbidades comorbidades = new Comorbidades();
    
    public Paciente(){
        super();
    }
    public Paciente(String nome, String cpf, LocalDate date, char sexo, Plano plano_saude){
        super(nome, cpf, date, sexo); 
        this.plano_saude = plano_saude;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointment) {
        appointments.add(appointment);
    }

    public Plano getPlano_saude() {
        return plano_saude;
    }

    public void setPlano_saude(Plano plano_saude) {
        this.plano_saude = plano_saude;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
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
    
}
