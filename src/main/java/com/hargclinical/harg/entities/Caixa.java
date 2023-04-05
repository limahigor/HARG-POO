package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.CaixaSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = CaixaSerializer.class)
public class Caixa implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double saldo;
    private boolean aberto;

    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL)
    private List<Orcamento> movimentacoes = new ArrayList<>();

    public Caixa(){
        this.saldo = 0.0;
        this.aberto = false;
    }
    
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public boolean getAberto() {
        return aberto;
    }
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public List<Orcamento> getMovimentacoes() {
        return movimentacoes;
    }

    public void addMovimentacoesCaixa(Orcamento orcamento){
        movimentacoes.add(orcamento);
    }

    public Long getId() {
        return id;
    }
}