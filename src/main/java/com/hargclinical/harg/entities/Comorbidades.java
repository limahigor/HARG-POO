package com.hargclinical.harg.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Comorbidades implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean tabagismo=false, obesidade=false, hipertensao=false, gestante=false, diabetes=false;
    private int idade, factorR=0;

    
     public Comorbidades() {
     }

    public Comorbidades(boolean tabagismo, boolean obesidade, boolean hipertensao, boolean gestante, boolean diabetes, int idade) {
        
        this.tabagismo = tabagismo;
        this.obesidade = obesidade;
        this.hipertensao = hipertensao;
        this.gestante = gestante; 
        this.diabetes = diabetes;
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }


    public void setIdade(int idade) {
        this.idade = idade;
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
        if(idade >= 60){
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
