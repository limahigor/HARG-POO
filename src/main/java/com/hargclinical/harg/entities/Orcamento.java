package com.hargclinical.harg.entities;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Orcamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected Paciente paciente;
    protected double valor;

    @ManyToOne
    @JoinColumn(name = "movimentacoes")
    private Caixa caixa;

    public Orcamento(){
    }

    public Orcamento(Paciente paciente){
        this.paciente = paciente;
    }

    public abstract void addOrcamento();
    
    public double getOrcamento(){
        return valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setOrcamento(double valor){
        this.valor = valor;
    }
    
    public String printOrcamento(){
        return "Valor total: R$ " + this.valor + "\n";
    }

}
