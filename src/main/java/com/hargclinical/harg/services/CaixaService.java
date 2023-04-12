package com.hargclinical.harg.services;

import java.util.List;

import com.hargclinical.harg.entities.Orcamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hargclinical.harg.entities.Caixa;

import com.hargclinical.harg.repositories.CaixaRepository;
import com.hargclinical.harg.services.exceptions.IllegalArgument;


@Service
public class CaixaService {
    @Autowired
    private CaixaRepository caixaRepository;

    public Caixa abrirCaixa() {
        List<Caixa> caixas = caixaRepository.findAll();
        Caixa caixa;

        if (caixas.isEmpty()){
            caixa = new Caixa();
            caixa.setAberto(true);
            caixa.setDataHoraAbertura();
            caixaRepository.save(caixa);
        }
        else{
            caixa = caixas.get(caixas.size()-1);
            if (caixa.getAberto()){
                throw new IllegalArgument("Caixa já está aberto");
            }
            else{
                caixa = new Caixa();
                caixa.setAberto(true);

                caixa.setDataHoraAbertura();

                caixaRepository.save(caixa);
            }
        }

        return caixa;
    }
    
    public Caixa fecharCaixa() {
        List<Caixa> caixas = caixaRepository.findAll();
        Caixa caixa = caixas.get(caixas.size() - 1);

        if (caixas.isEmpty()){
            throw new IllegalArgument("Não existe caixa a ser fechado");
        }

        if (!caixa.getAberto()) {
            throw new IllegalArgument("Caixa já está fechado");
        }

        caixa.setAberto(false);
        caixa.setDataHoraFechamento();
        caixaRepository.save(caixa);
        return caixa;
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
        }
        else{
            throw new IllegalArgument("Não é possível adicionar orçamento a um caixa fechado");
        }
    }
}
