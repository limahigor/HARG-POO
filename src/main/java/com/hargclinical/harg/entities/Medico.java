package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="medicos")
public class Medico extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String especializacao;

    public Medico(String nome, String cpf, int idade, char sexo, String especializacao){
        super(nome, cpf, idade, sexo);
        this.especializacao = especializacao;
        
    }
}
