package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private LocalDate data;
    private LocalTime hora;

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

    public void setAberto(boolean aberto, LocalDate data, LocalTime hora) {
        this.data = data;
        this.hora = hora;
        this.aberto = aberto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public List<Orcamento> getMovimentacoes() {
        return movimentacoes;
    }

    public void addMovimentacoesCaixa(Orcamento orcamento){
        movimentacoes.add(orcamento);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Caixa other = (Caixa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}