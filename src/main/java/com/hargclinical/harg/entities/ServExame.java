package com.hargclinical.harg.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.ServicesSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "serv_exames")
@PrimaryKeyJoinColumn(name = "id")
@JsonSerialize(using = ServicesSerializer.class)
public class ServExame extends Services {
    
    public ServExame(){
        super();
    }//
    
    public ServExame(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

}