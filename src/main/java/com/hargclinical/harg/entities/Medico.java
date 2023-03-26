package com.hargclinical.harg.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.MedicoSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="medicos")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = MedicoSerializer.class)
public class Medico extends Pessoa{
    String crm;
    String especializacao;

    @ManyToMany
	@JoinTable(
        name = "medico_servico",
        joinColumns = @JoinColumn(name = "medico_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
	private Set<Services> medico_servicos = new HashSet<>();

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public Medico(){
        super();
    }

    public Medico(String nome, String cpf, LocalDate date, char sexo, String especializacao, String crm){
        super(nome, cpf, date, sexo); 
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
        return medico_servicos;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointment) {
        appointments.add(appointment);
    }
    
}
