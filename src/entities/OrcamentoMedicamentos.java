package entities;

import java.util.List;

public class OrcamentoMedicamentos extends Orcamento{
    public OrcamentoMedicamentos(Paciente paciente, List<Prescricao> prescricao) {
        super(paciente);
        this.prescricao = prescricao;
    }
    
    @Override
    public double getOrcamento(){
        for(Prescricao medicamento : prescricao){
            this.valor += medicamento.valores();
        }
        return valor;
    }
    
}
