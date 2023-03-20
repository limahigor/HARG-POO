package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="pacientes")
public class Paciente extends Pessoa{

    public Paciente(String nome, String cpf, int idade, char sexo, String especializacao){
        super(nome, cpf, idade, sexo); 
    }
    
}
