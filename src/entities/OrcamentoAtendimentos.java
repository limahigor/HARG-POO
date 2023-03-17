package entities;

import entities_enums.Plano;

public class OrcamentoAtendimentos extends Orcamento{

    public OrcamentoAtendimentos(Paciente paciente) {
        super(paciente);
    }

    public void addOrcamento(Services procedimentos) {     
        this.procedimentos.add(procedimentos);
        
        if(paciente.plano_saude == Plano.NENHUM){
            this.valor += procedimentos.valor;
        }
        else if(paciente.plano_saude == Plano.SUS){
            this.valor += procedimentos.valor * 0.95;
        }
        else if(paciente.plano_saude == Plano.PARTICULAR){
            this.valor += procedimentos.valor * 0.9;
        }
        else{
            this.valor += procedimentos.valor * 0.85;
        }

    }

    public void removeOrcamento(Services procedimentos){
        if(paciente.plano_saude == Plano.NENHUM){
            valor -= procedimentos.valor;
        }
        else if(paciente.plano_saude == Plano.SUS){
            valor -= procedimentos.valor * 0.95;
        }
        else if(paciente.plano_saude == Plano.PARTICULAR){
            valor -= procedimentos.valor * 0.9;
        }
        else{
            valor -= procedimentos.valor * 0.85;
        }

    }

}
