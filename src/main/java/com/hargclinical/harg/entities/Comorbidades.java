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
    private int factorR;
    
    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente; 
    
    
     public Comorbidades() {
        this.tabagismo = false;
        this.obesidade = false;
        this.hipertensao = false;
        this.gestante = false;
        this.diabetes = false;
        this.factorR = 0;
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

    public boolean getIdade() {
        return idade;
    }

    public boolean getTabagismo() {
        return tabagismo;
    }

    public int getFactorR() {
            return factorR;
        }

    public void setTabagismo(boolean tabagismo) {
        this.tabagismo = tabagismo;
    }

    public void setFactorR(){
        if(idade){
            factorR += 1;
        }

        if(tabagismo){
            factorR += 1;
        }

        if(obesidade){
            factorR += 1;
        }

        if(hipertensao){
            factorR += 1;
        }
        
        if(gestante){
            factorR += 1;
        }

        if(diabetes){
            factorR += 1;
        }
    }

    public boolean getObesidade() {
        return obesidade;
    }


    public void setObesidade(boolean obesidade) {
        this.obesidade = obesidade;
    }


    public boolean getHipertensao() {
        return hipertensao;
    }


    public void setHipertensao(boolean hipertensao) {
        this.hipertensao = hipertensao;
    }


    public boolean getGestante() {
        return gestante;
    }


    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }


    public boolean getDiabetes() {
        return diabetes;
    }


    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }
}
