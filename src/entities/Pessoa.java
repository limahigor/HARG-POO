package entities;

import java.util.List;

public class Pessoa {
    public int rg, idade;
    public int telefone;
    public char sexo;
    public String cpf;
    public String nome, email;

    public Pessoa(String nome, String cpf, int idade, char sexo, Prioridade prioridade) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }

    public static Pessoa buscarCadastroCPF(List<Pessoa> list, String cpf) {
        for(Pessoa cadastro : list) {
            if (cadastro.cpf.equals(cpf)) {
                return cadastro;
            }
        }
        return null;
    }

    public static Pessoa buscarCadastroNome(List<Pessoa> list, String nome) {
        for(Pessoa cadastro : list) {
            if (cadastro.nome.equals(nome)) {
                return cadastro;
            }
        }
        return null;
    }

}
