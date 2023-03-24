package com.hargclinical.harg.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.serializables.PacienteSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pacientes")
@JsonSerialize(using = PacienteSerializer.class)
public class Paciente extends Pessoa{

    public Plano plano_saude;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public Paciente(){
        super();
    }

    public Paciente(String nome, String cpf, int idade, char sexo, Plano plano_saude){
        super(nome, cpf, idade, sexo); 
        this.plano_saude = plano_saude;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointment) {
        appointments.add(appointment);
    }
    
}
