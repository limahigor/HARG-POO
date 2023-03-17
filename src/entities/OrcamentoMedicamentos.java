package entities;

import java.util.List;
import java.util.Scanner;

public class OrcamentoMedicamentos extends Orcamento{
    protected List<Prescricao> prescricao;

    public OrcamentoMedicamentos(Paciente paciente, List<Prescricao> prescricao) {
        super(paciente);
        this.prescricao = prescricao;
    }
    
    
    public void addOrcamento(){
        for(Prescricao medicamento : prescricao){
            for(Double valor : medicamento.valores){
                Scanner sc = new Scanner(System.in);

                System.out.print("Quantidade: ");
                int quantidade = sc.nextInt();

                this.valor += valor * quantidade;

                sc.close();
            }
        }
    }

    public void removeOrcamento(String removerMedicamento){
        int i = 0;
        for(Prescricao remedio : prescricao){
            if(remedio.nomes.contains(removerMedicamento)){
                Scanner sc = new Scanner(System.in);

                System.out.print("Quantidade: ");
                int quantidade = sc.nextInt();

                valor -= remedio.valores.get(i) * quantidade;

                sc.close();
            }
            i++;
        }
    }
}
