package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.List;

import com.hargclinical.harg.entities_enums.Plano;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity  
@Table(name = "orcamentos")  
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Orcamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected double valor;

    public Orcamento(){
        super();
    }

    public double getValor() {
        return valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void gerarOrcamento(List<Double> valores, Plano planoSaude) {
        double total = 0;

        for (double indexValor : valores) {
            switch (planoSaude.getCode()) {
                case 1 -> total += indexValor * 0.95;
                case 2 -> total += indexValor * 0.90;
                case 3 -> total += indexValor * 0.85;
                case 4 -> total += indexValor * 0.80;
                default -> total += indexValor;
            }
        }

         this.valor = total;
    }
}
