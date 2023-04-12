package com.hargclinical.harg.entities;

import com.hargclinical.harg.entities_enums.Plano;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orcamento_medicamentos")
@PrimaryKeyJoinColumn(name = "id")
public class OrcamentoMedicamentos extends Orcamento{

    @OneToMany(mappedBy = "orcamentos", cascade = CascadeType.ALL)
    private List<Prescricao> prescricao = new ArrayList<>();

    public OrcamentoMedicamentos(){
        super();
    }

    @Override
    public void gerarOrcamento(List<Double> valores, Plano planoSaude) {
        double total = 0;

        for (double indexValor : valores) {
            switch (planoSaude.getCode()) {
                case 1 -> total  += indexValor * 0.90;
                case 2 -> total  += indexValor * 0.85;
                case 3 -> total  += indexValor * 0.80;
                case 4 -> total  += indexValor * 0.75;
                default -> total += indexValor;
            }
        }

        this.valor = total;
    }

    public List<Prescricao> getPrescricao() {
        return prescricao;
    }


}

