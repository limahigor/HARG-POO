package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<Dias> dias;

    public Agenda() {
        this.dias = new ArrayList<>();
        LocalDate inicioMes = LocalDate.now().withDayOfMonth(1);
        
        for (int i = 0; i < inicioMes.lengthOfMonth(); i++) {
            LocalDate data = inicioMes.plusDays(i);
            Dias dia = new Dias(data, new ArrayList<>());
            this.dias.add(dia);
        }
    }

    public void agendarConsulta(int diaDoMes, Appointment consulta) {
        if (diaDoMes < 1 || diaDoMes > this.dias.size()) {
            throw new IllegalArgumentException("Dia do mês inválido!");
        }

        Dias dia = this.dias.get(diaDoMes - 1);
        dia.getConsultas().add(consulta);
    }

    public List<Appointment> listarConsultas(LocalDate data) {
        Dias dia = encontrarDia(data);
        if (dia != null) {
            return dia.getConsultas();
        } else {
            return Collections.emptyList();
        }
    }
    
    private Dias encontrarDia(LocalDate data) {
        for (Dias dia : this.dias) {
            if (dia.getData().equals(data)) {
                return dia;
            }
        }
        return null;
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
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}