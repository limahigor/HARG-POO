package entities;

import java.util.List;

public class Medico extends Pessoa {
    private String crm;
    protected String especializacao;

    public Medico(String nome, String cpf, int idade, char sexo, String crm, String especializacao) {
        super(nome, cpf, idade, sexo);
        this.crm = crm;
        this.especializacao = especializacao;
    }

    public String toString(){

        return "Nome: " + nome + "\n" + 
               "Idade: " + idade + "\n" + 
               "Sexo: " + sexo + "\n" + 
               "CPF: " + cpf + "\n" +
               "CRM: " + crm + "\n" +
               "Especilizacao: " + especializacao;
    }
    
    public static Pessoa buscarCadastroEsp(List<Pessoa> allPessoas, String nome) {
        for(Pessoa cadastro : allPessoas) {
            Medico pessoa = (Medico)cadastro;

            if (pessoa.especializacao.equals(nome)) {
                return cadastro;
            }

        }
        return null;
    }
}
