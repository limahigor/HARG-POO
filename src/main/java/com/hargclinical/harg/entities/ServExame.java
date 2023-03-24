package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "serv_exames")
@PrimaryKeyJoinColumn(name = "id")
public class ServExame extends Services {
    
    public ServExame(){
        super();
    }
    
    public ServExame(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

}