package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "serv_consultas")
@PrimaryKeyJoinColumn(name = "id")
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