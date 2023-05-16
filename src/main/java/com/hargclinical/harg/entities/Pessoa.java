package com.hargclinical.harg.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@MappedSuperclass
public abstract class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome = "";
    
    @Column(unique=true)
    private String cpf = "";

    private char sexo;
    private long idade;

    public Pessoa(){
    }

    public Pessoa(String nome, String cpf, LocalDate data, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = definirIdade(data);
    }

    public Pessoa(Paciente paciente){
        this.cpf = paciente.getCpf();
        this.nome = paciente.getNome();
        this.sexo = paciente.getSexo();
        this.idade = paciente.getIdade();
    }

    public static long definirIdade(LocalDate data) {
        LocalDate dateNow = LocalDate.now();

        return ChronoUnit.YEARS.between(data, dateNow);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name){
        this.nome = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public long getIdade() {
        return idade;
    }

    public LocalDate getDataNascimento(){
        return LocalDate.now().minusYears(idade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
