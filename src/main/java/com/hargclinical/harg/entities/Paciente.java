package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="pacientes")
public class Paciente extends Pessoa{

    public Paciente(){
        super();
    }

    public Paciente(String nome, String cpf, int idade, char sexo){
        super(nome, cpf, idade, sexo); 
    }
    
}
