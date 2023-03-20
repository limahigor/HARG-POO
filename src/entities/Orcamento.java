package entities;

import java.util.ArrayList;
import java.util.List;


public abstract class Orcamento {
    protected Paciente paciente;
    protected double valor;

    public Orcamento(Paciente paciente) {
        this.paciente = paciente;
        this.valor = 0;
    }

    public abstract void addOrcamento();
    
    public double getOrcamento(){
        return valor;
    }

    public void setOrcamento(double valor){
        this.valor = valor;
    }
    
    public String printOrcamento(){
        return "Valor total: R$ " + this.valor + "\n";
    }
}
