package entities;

import java.util.List;

public class OrcamentoMedicamentos extends Orcamento{
    protected List<Prescricao> prescricao;

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

    public void removeOrcamento(String removerMedicamento){
        int i = 0;
        for(Prescricao remedio : prescricao){
            if(remedio.nomes.contains(removerMedicamento)){
                valor -= remedio.valores.get(i);
            }
            i++;
        }

    }
    
}
