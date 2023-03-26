package com.hargclinical.harg.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.ServConsultaSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "serv_consultas")
@PrimaryKeyJoinColumn(name = "id")
@JsonSerialize(using = ServConsultaSerializer.class)
public class ServConsulta extends Services {
    private int time;

    public ServConsulta() {
        super();
    }
    
    public ServConsulta(String nome, String especialidade, double valor, int time) {
        super(nome, especialidade, valor);
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}