package com.hargclinical.harg.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="medicos")
public class Medico extends Pessoa{
    String crm;
    String especializacao;

    @JsonIgnore
    @ManyToMany
	@JoinTable(name = "tb_medico_servico", joinColumns = @JoinColumn(name = "medico_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
	private Set<Services> servicos = new HashSet<>();

    public Medico(){
        super();
    }

    public Medico(String nome, String cpf, int idade, char sexo, String especializacao, String crm){
        super(nome, cpf, idade, sexo); 
        this.especializacao = especializacao;
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public Set<Services> getServicos() {
        return servicos;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }    
    
}
