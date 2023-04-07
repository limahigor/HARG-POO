package com.hargclinical.harg.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.MedicoSerializer;

import jakarta.persistence.*;

@Entity
@Table(name="medicos")
@PrimaryKeyJoinColumn(name = "medicoId")
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
	private final Set<Services> medicoServicos = new HashSet<>();

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private final List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private final List<Prescricao> prescricoes = new ArrayList<>();

    @OneToOne(mappedBy = "medico", cascade = CascadeType.ALL)
    private Agenda agenda;

    public Medico(){
        super();
    }

    public Medico(String nome, String cpf, LocalDate date, char sexo, String especializacao, String crm){
        super(nome, cpf, date, sexo); 
        this.especializacao = especializacao;
        this.crm = crm;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public Set<Services> getServicos() {
        return medicoServicos;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }
}
