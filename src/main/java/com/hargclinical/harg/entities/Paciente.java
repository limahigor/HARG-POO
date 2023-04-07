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
@PrimaryKeyJoinColumn(name = "pessoaId")
@JsonSerialize(using = PacienteSerializer.class)
public class Paciente extends Pessoa{

    private Plano planoSaude;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Prontuario prontuario;
    
    public Paciente(){
        super();
    }
    public Paciente(String nome, String cpf, LocalDate date, char sexo, Plano planoSaude){
        super(nome, cpf, date, sexo); 
        this.planoSaude = planoSaude;
    }

    public Plano getPlanoSaude() {
        return planoSaude;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario() {
        this.prontuario = new Prontuario(this);
    }

    public int getFactorRisco(){
       return prontuario.getComorbidades().getFactorRisco();
    }



}
