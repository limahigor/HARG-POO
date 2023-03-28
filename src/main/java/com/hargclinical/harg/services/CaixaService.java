package com.hargclinical.harg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Caixa;
import com.hargclinical.harg.entities.MovimentacaoCaixa;
import com.hargclinical.harg.repositories.CaixaRepository;


@Service
public class CaixaService {
    @Autowired
    private CaixaRepository caixaRepository;
    
    @Autowired
    private MovCaixaService service;
    
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
        List<MovimentacaoCaixa> movimentacoes = service.findAlList();
        Double saldo = caixa.getSaldo();
        for (MovimentacaoCaixa movimentacao : movimentacoes) {
            saldo += movimentacao.getValor();
        }
        return saldo;
    }
}