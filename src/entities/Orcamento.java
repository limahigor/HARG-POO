package entities;

import java.util.List;


public class Orcamento {
    protected List<Services> procedimentos;
    protected Paciente paciente;
    protected double valor;

    public Orcamento(Paciente paciente) {
        this.paciente = paciente;
        this.valor = 0;
    }

    public double getOrcamento(){
        return valor;
    }

    public void zerarOrcamento(){
        this.valor = 0;
    }
    
    public String printOrcamento(){
        return "Valor total: R$ " + this.valor + "\n";
    }
}
