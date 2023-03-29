package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appointments")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToMany(mappedBy = "consultas", cascade = CascadeType.ALL)
    private List<Dias> dia = new ArrayList<>();
        
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;

    private LocalDate data;
    private LocalTime horario;
    
    /*public String cpfPaciente;
    public String nomeMedico;
    public String nomeServico;*/

    public Appointment (){

    }

    public Appointment (Medico medico, Paciente paciente, Services service, 
                        LocalDate data, LocalTime horario){
        Random random = new Random();

        this.id = (long) random.nextInt(999999999);
        this.medico = medico;
        this.paciente = paciente;
        this.service = service;
        this.data = data;
        this.horario = horario;
    }

    // public String toString (Paciente paciente){
    //     StringBuilder sb = new StringBuilder();

    //     sb.append("ID da consulta: " + id + "\n");
    //     sb.append("Nome do paciente: " + paciente.nome + "\n");
    //     sb.append("Nome do médico: " + medico.nome + "\n");
    //     sb.append("Procedimento: " + service.nome + "\n");
    //     sb.append("Data da consulta: " + dia + "/" + mes + "/" + ano + "\n");
    //     sb.append("Horário: " + hora + ":" + minuto + "\n");

    //     return sb.toString();
    // }

    public List<Dias> getDia() {
        return dia;
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

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
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