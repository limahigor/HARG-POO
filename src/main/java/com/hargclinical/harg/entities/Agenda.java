package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.AgendaSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "agenda")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = AgendaSerializer.class)
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<Dias> dias = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Services service;

    public Agenda() {

    }

    public void createAgenda(){
        List<Dias> dias = new ArrayList<>();
        setListaDias(dias, this);
        setDias(dias);
    }

    public void setListaDias(List<Dias> listaDias, Agenda agenda) {
         LocalDate inicioMes = LocalDate.now().withDayOfMonth(1);
        
        for (int i = 0; i < inicioMes.lengthOfMonth(); i++) {
            Dias dia = new Dias();
            dia.setDia(i + 1);
            dia.setAgenda(agenda);
            listaDias.add(dia);
        }
    }

    public void agendarConsulta(Appointment consulta) {
        if (consulta.getData().getDayOfMonth() < 1 || consulta.getData().getDayOfMonth() > this.dias.size()) {
            throw new IllegalArgumentException("Dia do mês inválido!");
        }

        Dias dia = this.dias.get(consulta.getData().getDayOfMonth() - 1);
        dia.getConsultas().add(consulta);
        consulta.getDia().add(dia);
    }

    public static List<Dias> listarConsultasPorMedico(Medico medico) {
        Agenda agendaMedico = medico.getAgenda();
        return agendaMedico.getDias();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dias> getDias() {
        return dias;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public void setDias(List<Dias> dias) {
        this.dias = dias;
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
        Agenda other = (Agenda) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

}