package com.hargclinical.harg.entities;

import java.util.List;

public class Caixa {
    double caixa;

    public Caixa() {
        this.caixa = 0;
    }

    public void addCaixa(Orcamento orcamento) {
        caixa += orcamento.valor;
    }

    // public void clearList(List<Orcamento> listaOrcamento) {
    //     for(int i = 0; i < listaOrcamento.size(); i++){
    //         System.out.printf("Orçamento %d\n", (i+1));
    //         for(int j = 0; j < listaOrcamento.get(i).procedimentos.size(); j++)
    //             System.out.printf("Procedimento %s R$%.2f\n", listaOrcamento.get(i).procedimentos.get(j).nome, listaOrcamento.get(i).procedimentos.get(j).valor);
    //         System.out.printf("Valor total: R$%.2f\n", listaOrcamento.get(i).valor);
    //         System.out.println("\n=======================================");
    //     }

    //     System.out.println();
    //     System.out.printf("Total de dinheiro em caixa: R$%.2f\n", caixa);
    //     listaOrcamento.clear();
    //     this.caixa = 0;
    // }
}