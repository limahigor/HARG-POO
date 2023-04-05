package com.hargclinical.harg.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hargclinical.harg.entities.Caixa;

import com.hargclinical.harg.repositories.CaixaRepository;


@Service
public class CaixaService {
    @Autowired
    private CaixaRepository caixaRepository;

    public void abrirCaixa() {
        List<Caixa> caixas = caixaRepository.findAll();
        Caixa caixa;

        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
        
        if (caixas.isEmpty()){
            caixa = new Caixa();
            caixa.setAberto(true);
            caixa.setDataHoraAberto(data, hora);
            caixaRepository.save(caixa);
        }
        else{
            caixa = caixas.get(caixas.size()-1);
            if (caixa.getAberto()){
                throw new RuntimeException("Caixa já está aberto");
            }
            else{
                caixa = new Caixa();
                caixa.setAberto(true);
                caixa.setDataHoraAberto(data, hora);
                caixaRepository.save(caixa);
            }
        }

    }
    
    public void fecharCaixa() {
        List<Caixa> caixas = caixaRepository.findAll();
        Caixa caixa = caixas.get(caixas.size()-1);

        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());

        if (caixas.isEmpty()){
            throw new RuntimeException("Não existe caixa a ser fechado");
        }

        if (!caixa.getAberto()) {
            throw new RuntimeException("Caixa já está fechado");
        }

        caixa.setAberto(false);
        caixa.setDataHoraFechado(data, hora);
        Double saldoFinal = calcularSaldoFinal(caixa);
        caixa.setSaldo(saldoFinal);
        caixaRepository.save(caixa);
    }
    
    private Double calcularSaldoFinal(Caixa caixa) {
        Double saldo = caixa.getSaldo();
        for (int i = 0; i < caixa.getMovimentacoes().size(); i++) {
            saldo += caixa.getMovimentacoes().get(i).getValor();
        }
        
        return saldo;
    }

    public List<Caixa> findAll() {
        return caixaRepository.findAll();
    }
}