package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Comorbidades implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean tabagismo, obesidade, hipertensao, gestante, diabetes, idade;
    private int factorRisco;
    
    @OneToOne
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;
    
    
     public Comorbidades() {
        this.tabagismo = false;
        this.obesidade = false;
        this.hipertensao = false;
        this.gestante = false;
        this.diabetes = false;
        this.factorRisco = 0;
        this.idade = false;
     }

    public Comorbidades(boolean tabagismo, boolean obesidade, boolean hipertensao, boolean gestante, boolean diabetes, LocalDate idade) {
        
        this.tabagismo = tabagismo;
        this.obesidade = obesidade;
        this.hipertensao = hipertensao;
        this.gestante = gestante; 
        this.diabetes = diabetes;
        if(Pessoa.definirIdade(idade) >=60)this.idade = true;
        else this.idade = false;
        setFactorR();
        
    }

    public void setFactorR(){
        if(idade){
            factorRisco += 1;
        }

        if(tabagismo){
            factorRisco += 1;
        }

        if(obesidade){
            factorRisco += 1;
        }

        if(hipertensao){
            factorRisco += 1;
        }
        
        if(gestante){
            factorRisco += 1;
        }

        if(diabetes){
            factorRisco += 1;
        }
    }

    public Long getId() {
        return id;
    }

    public boolean isTabagismo() {
        return tabagismo;
    }

    public void setTabagismo(boolean tabagismo) {
        this.tabagismo = tabagismo;
    }

    public boolean isObesidade() {
        return obesidade;
    }

    public void setObesidade(boolean obesidade) {
        this.obesidade = obesidade;
    }

    public boolean isHipertensao() {
        return hipertensao;
    }

    public void setHipertensao(boolean hipertensao) {
        this.hipertensao = hipertensao;
    }

    public boolean isGestante() {
        return gestante;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isIdade() {
        return idade;
    }

    public void setIdade(boolean idade) {
        this.idade = idade;
    }

    public int getFactorRisco() {
        return factorRisco;
    }

    public void setFactorRisco(int factorR) {
        this.factorRisco = factorR;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }
}
