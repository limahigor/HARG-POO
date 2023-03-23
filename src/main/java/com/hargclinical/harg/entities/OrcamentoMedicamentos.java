package com.hargclinical.harg.entities;

import java.util.Scanner;

import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orcamento_medicamentos")
public class OrcamentoMedicamentos extends Orcamento{

    @OneToOne
    @MapsId
    private Prescricao prescricao; 

    public OrcamentoMedicamentos(){
        
    }
    
    public OrcamentoMedicamentos(Paciente paciente, Prescricao prescricao) {
        super(paciente);
        this.prescricao = prescricao;
    }
    
    @Override
    public void addOrcamento(){
        for(int i = 0; i < prescricao.nomes.size() ; i++){
            Scanner sc = new Scanner(System.in);
            int quantidade = sc.nextInt();

            this.valor = prescricao.valores.get(i) * quantidade;

            sc.close();
        }
    }

    public void removeOrcamento(String removerMedicamento){
        for(int i = 0; i < prescricao.nomes.size() ; i++){
            if(prescricao.nomes.contains(removerMedicamento)){
                Scanner sc = new Scanner(System.in);
                int quantidade = sc.nextInt();

                valor -= prescricao.valores.get(i) * quantidade;

                sc.close();
            }
        }
    }
}

