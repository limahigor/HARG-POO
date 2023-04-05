package com.hargclinical.harg.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.hargclinical.harg.entities.Orcamento;
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
            caixa.setDataHoraAbertura();
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

                caixa.setDataHoraAbertura();

                caixaRepository.save(caixa);
            }
        }

    }
    
    public void fecharCaixa() {
        List<Caixa> caixas = caixaRepository.findAll();
        Caixa caixa = caixas.get(caixas.size() - 1);

        if (caixas.isEmpty()){
            throw new RuntimeException("Não existe caixa a ser fechado");
        }

        if (!caixa.getAberto()) {
            throw new RuntimeException("Caixa já está fechado");
        }

        caixa.setAberto(false);
        caixa.setDataHoraFechamento();
        caixaRepository.save(caixa);
    }
    
    public Double calcularSaldoFinal(Caixa caixa) {
        double saldo = 0;

        for(Orcamento orcamento : caixa.getMovimentacoes()){
            saldo += orcamento.getValor();
        }
        
        return saldo;
    }

    public List<Caixa> findAll() {
        return caixaRepository.findAll();
    }

    public Caixa findAberto(){
        List<Caixa> caixas = caixaRepository.findAll();

        if(caixas.get(caixas.size()-1).getAberto())
            return caixas.get(caixas.size()-1);
        else
            return null;
    }

    public void addOrcamento(Orcamento orcamento){
        Caixa caixa = findAberto();
        if(caixa != null){
            caixa.addMovimentacoesCaixa(orcamento);
            caixa.setSaldo(calcularSaldoFinal(caixa));
            orcamento.setCaixa(caixa);
//            caixaRepository.save(caixa);
        }
    }
}