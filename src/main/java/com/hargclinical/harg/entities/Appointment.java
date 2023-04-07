package com.hargclinical.harg.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.AppointmentSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appointments")
@JsonSerialize(using = AppointmentSerializer.class)
public class Appointment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private OrcamentoServicos orcamentos;

    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;
    
    @ManyToMany(mappedBy = "consultas", cascade = CascadeType.ALL)
    private List<Dias> dia = new ArrayList<>();
        
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;

    private boolean orcamentoGerado = false;

    private LocalDate data;
    private LocalTime horario;

    public Appointment (){

    }

    public Appointment (Medico medico, Prontuario prontuario, Services service,
                        LocalDate data, LocalTime horario){
        Random random = new Random();

        this.id = (long) random.nextInt(999999999);
        this.medico = medico;
        this.prontuario = prontuario;
        this.service = service;
        this.data = data;
        this.horario = horario;
    }

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
    
    public Prontuario getProntuario() {
        return prontuario;
    }
    
    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }
    
    public Services getService() {
        return service;
    }
    
    public void setService(Services service) {
        this.service = service;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public OrcamentoServicos getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(OrcamentoServicos orcamentos) {
        this.orcamentos = orcamentos;
    }

    public void setDia(List<Dias> dia) {
        this.dia = dia;
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
        Appointment other = (Appointment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public boolean isOrcamentoGerado() {
        return orcamentoGerado;
    }

    public void setOrcamentoGerado(boolean orcamentoGerado) {
        this.orcamentoGerado = orcamentoGerado;
    }

    
    
}