package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="servicos_procedimentos")
public class ServProcedimento extends Services {

    public ServProcedimento(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

}
