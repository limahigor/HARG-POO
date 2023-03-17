package entities;

import java.util.List;

public class OrcamentoMedicamentos extends Orcamento{
    private List<Prescricao> prescricao;

    public OrcamentoMedicamentos(Paciente paciente, List<Prescricao> prescricao) {
        super(paciente);
        this.prescricao = prescricao;
    }
    
    
    public void addOrcamento(){
        for(Prescricao medicamento : prescricao){
            for(Double valor : medicamento.valores){
                this.valor += valor;
            }
        }
    }
    
}
