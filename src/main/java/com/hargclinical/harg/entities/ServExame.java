package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="servicos_exames")
public class ServExame extends Services {

    public ServExame(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

}