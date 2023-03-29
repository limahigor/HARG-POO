package com.hargclinical.harg.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="orcamento_medicamentos")
@PrimaryKeyJoinColumn(name = "id")
public class OrcamentoMedicamentos extends Orcamento{

    @OneToOne
    @JoinColumn(name = "prescricao_id")
    private Prescricao prescricao; 

    public OrcamentoMedicamentos(){
        super();
    }

    public Prescricao getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(Prescricao prescricao) {
        this.prescricao = prescricao;
    }
    
    // @Override
    // public void addOrcamento(){
    //     for(int i = 0; i < prescricao.nomes.size() ; i++){
    //         Scanner sc = new Scanner(System.in);
    //         int quantidade = sc.nextInt();

    //         this.valor = prescricao.valores.get(i) * quantidade;

    //         sc.close();
    //     }
    // }

    /*public void removeOrcamento(String removerMedicamento){
        for(int i = 0; i < prescricao.nomes.size() ; i++){
            if(prescricao.nomes.contains(removerMedicamento)){
                Scanner sc = new Scanner(System.in);
                int quantidade = sc.nextInt();

                valor -= prescricao.valores.get(i) * quantidade;

                sc.close();
            }
        }
    }*/
}

