package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="servicos_consultas")
public class ServConsulta extends Services {
    private int time;

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