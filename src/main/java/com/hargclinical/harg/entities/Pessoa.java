package com.hargclinical.harg.entities;

import java.io.Serializable;

public class Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;

    public int idade;
    public char sexo;
    public String cpf;
    public String nome;

    public Pessoa(String nome, String cpf, int idade, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
