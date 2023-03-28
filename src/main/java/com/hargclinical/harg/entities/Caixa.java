package com.hargclinical.harg.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity
public class Caixa {
    @Id
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

    public void addMovimentacao(Orcamento movimentacao){
        movimentacoes.add(movimentacao);
    }

    public List<Orcamento> getMovimentacoes() {
        return movimentacoes;
    }

}