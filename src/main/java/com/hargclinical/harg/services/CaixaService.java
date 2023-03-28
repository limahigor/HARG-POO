package com.hargclinical.harg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hargclinical.harg.entities.Caixa;

import com.hargclinical.harg.repositories.CaixaRepository;


@Service
public class CaixaService {
    @Autowired
    private CaixaRepository caixaRepository;
    
    public Caixa abrirCaixa() {
        Caixa caixa = new Caixa();
        if (caixa.getAberto()) {
            throw new RuntimeException("Caixa já está aberto");
        }
        caixa.setAberto(true);
        return caixaRepository.save(caixa);
    }
    
    public Caixa fecharCaixa() {
        Caixa caixa = new Caixa();
        if (!caixa.getAberto()) {
            throw new RuntimeException("Caixa já está fechado");
        }
        caixa.setAberto(false);
        Double saldoFinal = calcularSaldoFinal(caixa);
        caixa.setSaldo(saldoFinal);
        return caixaRepository.save(caixa);
    }
    
    private Double calcularSaldoFinal(Caixa caixa) {
        Double saldo = caixa.getSaldo();
        for (int i = 0; i < caixa.getMovimentacoes().size(); i++) {
            saldo += caixa.getMovimentacoes().get(i).getValor();
        }
        
        return saldo;
    }
}