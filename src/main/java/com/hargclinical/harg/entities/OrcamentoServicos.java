package com.hargclinical.harg.entities;

import java.util.HashSet;
import java.util.Set;

import com.hargclinical.harg.entities_enums.Plano;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orcamento_servicos")
public class OrcamentoServicos extends Orcamento {

    @OneToMany(mappedBy = "orcamento")
    protected Set<Services> procedimentos = new HashSet<>();

    public OrcamentoServicos() {
        
    }

    public OrcamentoServicos(Paciente paciente) {
        super(paciente);
    }

    public void addProcedimento(Services procedimentos){
        this.procedimentos.add(procedimentos);
    }

    @Override
    public void addOrcamento() { 

        for(Services procedimento : procedimentos){
            if(paciente.plano_saude == Plano.BRONZE){
                this.valor += procedimento.valor;
            }
            else if(paciente.plano_saude == Plano.PRATA){
                this.valor += procedimento.valor * 0.95;
            }
            else if(paciente.plano_saude == Plano.OURO){
                this.valor += procedimento.valor * 0.9;
            }
            else{
                this.valor += procedimento.valor * 0.85;
            }
        }

    }

    public void removeOrcamento(String removerProcedimento){
        for(Services procedimento : procedimentos){
            if(procedimento.nome.equals(removerProcedimento)){

                if(paciente.plano_saude == Plano.BRONZE){
                    valor -= procedimento.valor;
                }
                else if(paciente.plano_saude == Plano.PRATA){
                    valor -= procedimento.valor * 0.95;
                }
                else if(paciente.plano_saude == Plano.OURO){
                    valor -= procedimento.valor * 0.9;
                }
                else{
                    valor -= procedimento.valor * 0.85;
                }
            }
        }
    }
}
