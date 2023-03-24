package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "serv_procedimentos")
@PrimaryKeyJoinColumn(name = "id")
public class ServProcedimento extends Services {

    public ServProcedimento (){
        super();
    }
    
    public ServProcedimento(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

}
