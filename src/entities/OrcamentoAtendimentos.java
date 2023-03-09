package entities;

public class OrcamentoAtendimentos extends Orcamento{

    public OrcamentoAtendimentos(Paciente paciente) {
        super(paciente);
    }


    public void getOrcamento(Services procedimentos) {     
        this.procedimentos.add(procedimentos);
        
        if(paciente.plano_saude == 0){
            this.valor += procedimentos.valor;
        }
        else if(paciente.plano_saude == 1){
            this.valor += procedimentos.valor * 0.95;
        }
        else if(paciente.plano_saude == 2){
            this.valor += procedimentos.valor * 0.9;
        }
        else{
            this.valor += procedimentos.valor * 0.85;
        }
    }

    public void removeOrcamento(Services procedimentos){
        if(paciente.plano_saude == 0){
            valor -= procedimentos.valor;
        }
        else if(paciente.plano_saude == 1){
            valor -= procedimentos.valor * 0.95;
        }
        else if(paciente.plano_saude == 2){
            valor -= procedimentos.valor * 0.9;
        }
        else{
            valor -= procedimentos.valor * 0.85;
        }
    }


}
