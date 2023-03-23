package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Services implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String nome;
    public String especialidade;
    public List<String> profissionais = new ArrayList<>();
    public double valor;

    @ManyToOne
    @JoinColumn(name = "servicos")
    protected OrcamentoServicos orcamento;

    public Services() {
        
    }

    public Services(String nome, String especialidade, double valor) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.valor = valor;
    }

    public void addProfissional(Pessoa profissional) {
        profissionais.add(profissional.nome);
    }

    public boolean verificarProfissional(String nomeMedico) {

        if(profissionais.contains(nomeMedico)) {
            return true;
        }else{
            return false;
        }

    }

    public void printService() {
        System.out.println("Procedimento: " + nome);

        if (profissionais.size() != 0) {
            System.out.println("Profissionais: ");

            for(String nome : profissionais) {
                    System.out.println(nome);
            }
        }

        System.out.println("Valor: R$" + valor + "\n");
    }

    public static Services buscarProcedimentoNome(List<Services> allServices, String nome) {
         for (Services search : allServices) {
             if (search.nome.equals(nome)) {
                 return search;
             }
         }
         return null;
    }

    public static Services buscarProcedimentoEsp(List<Services> allServices, String x) {
        for (Services search : allServices) {
            if (search.especialidade.equals(x)) {
                return search;
            }
        }
        return null;
    }
    
}
