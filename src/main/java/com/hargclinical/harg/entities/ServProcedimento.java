package com.hargclinical.harg.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.ServicesSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "serv_procedimentos")
@PrimaryKeyJoinColumn(name = "id")
@JsonSerialize(using = ServicesSerializer.class)
public class ServProcedimento extends Services {

    public ServProcedimento (){
        super();
    }
    
    public ServProcedimento(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

}
