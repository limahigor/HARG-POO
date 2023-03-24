package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appointments")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int dia, mes, ano, hora, minuto;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private Services service;

    /*public String cpfPaciente;
    public String nomeMedico;
    public String nomeServico;*/
    

    public Appointment (){

    }
    public Appointment (Medico medico, Paciente paciente, Services service,
                       int dia, int mes, int ano, int hora, int minuto){
        Random random = new Random();

        this.id = (long) random.nextInt(999999999);
        this.medico = medico;
        this.paciente = paciente;
        this.service = service;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minuto = minuto;
    }

    public String toString (Paciente paciente){
        StringBuilder sb = new StringBuilder();

        sb.append("ID da consulta: " + id + "\n");
        sb.append("Nome do paciente: " + paciente.nome + "\n");
        sb.append("Nome do médico: " + medico.nome + "\n");
        sb.append("Procedimento: " + service.nome + "\n");
        sb.append("Data da consulta: " + dia + "/" + mes + "/" + ano + "\n");
        sb.append("Horário: " + hora + ":" + minuto + "\n");

        return sb.toString();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public int getDia() {
        return dia;
    }
    
    public void setDia(int dia) {
        this.dia = dia;
    }
    
    public int getMes() {
        return mes;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public int getAno() {
        return ano;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public int getHora() {
        return hora;
    }
    
    public void setHora(int hora) {
        this.hora = hora;
    }
    
    public int getMinuto() {
        return minuto;
    }
    
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
    public Medico getMedico() {
        return medico;
    }
    
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Services getService() {
        return service;
    }
    
    public void setService(Services service) {
        this.service = service;
    }
    

}

