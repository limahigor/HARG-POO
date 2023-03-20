package entities;

import java.util.ArrayList;
import java.util.List;

import entities_enums.Plano;

public class OrcamentoAtendimentos extends Orcamento{
    protected List<Services> procedimentos;

    public OrcamentoAtendimentos(Paciente paciente) {
        super(paciente);
        this.procedimentos = new ArrayList<>();
    }

    public void addProcedimento(Services procedimentos){
        this.procedimentos.add(procedimentos);
    }

    @Override
    public void addOrcamento() { 

        for(Services procedimento : procedimentos){
            if(paciente.plano_saude == Plano.NENHUM){
                this.valor += procedimento.valor;
            }
            else if(paciente.plano_saude == Plano.SUS){
                this.valor += procedimento.valor * 0.95;
            }
            else if(paciente.plano_saude == Plano.PARTICULAR){
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

                if(paciente.plano_saude == Plano.NENHUM){
                    valor -= procedimento.valor;
                }
                else if(paciente.plano_saude == Plano.SUS){
                    valor -= procedimento.valor * 0.95;
                }
                else if(paciente.plano_saude == Plano.PARTICULAR){
                    valor -= procedimento.valor * 0.9;
                }
                else{
                    valor -= procedimento.valor * 0.85;
                }
            }
        }
    }
}
