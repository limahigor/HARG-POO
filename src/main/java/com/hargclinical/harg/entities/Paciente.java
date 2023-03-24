package com.hargclinical.harg.entities;

import java.util.List;

import com.hargclinical.harg.entities_enums.Plano;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pacientes")
public class Paciente extends Pessoa{

    public Plano plano_saude;

    @OneToMany(mappedBy = "paciente")
    private List<Appointment> appointments;

    public Paciente(){
        super();
    }

    public Paciente(String nome, String cpf, int idade, char sexo, Plano plano_saude){
        super(nome, cpf, idade, sexo); 
        this.plano_saude = plano_saude;
    }
    
}
