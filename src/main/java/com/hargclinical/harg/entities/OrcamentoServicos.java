package com.hargclinical.harg.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hargclinical.harg.entities_enums.Plano;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="orcamento_servicos")
@PrimaryKeyJoinColumn(name = "id")
public class OrcamentoServicos extends Orcamento {

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "procedimentos_orcamentos",
               joinColumns = @JoinColumn(name = "orcamento_id"),
               inverseJoinColumns = @JoinColumn(name = "service_id"))
    protected Set<Appointment> appointm = new HashSet<>();*/

    @OneToMany(mappedBy = "orcamentos", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public OrcamentoServicos() {
        
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    

    // @Override
    // public void addOrcamento() { 

    //     for(Services procedimento : procedimentos){
    //         if(paciente.plano_saude == Plano.BRONZE){
    //             this.valor += procedimento.valor;
    //         }
    //         else if(paciente.plano_saude == Plano.PRATA){
    //             this.valor += procedimento.valor * 0.95;
    //         }
    //         else if(paciente.plano_saude == Plano.OURO){
    //             this.valor += procedimento.valor * 0.9;
    //         }
    //         else{
    //             this.valor += procedimento.valor * 0.85;
    //         }
    //     }

    // }

    // public void removeOrcamento(String removerProcedimento){
    //     for(Services procedimento : procedimentos){
    //         if(procedimento.nome.equals(removerProcedimento)){

    //             if(paciente.plano_saude == Plano.BRONZE){
    //                 valor -= procedimento.valor;
    //             }
    //             else if(paciente.plano_saude == Plano.PRATA){
    //                 valor -= procedimento.valor * 0.95;
    //             }
    //             else if(paciente.plano_saude == Plano.OURO){
    //                 valor -= procedimento.valor * 0.9;
    //             }
    //             else{
    //                 valor -= procedimento.valor * 0.85;
    //             }
    //         }
    //     }
    // }
}
