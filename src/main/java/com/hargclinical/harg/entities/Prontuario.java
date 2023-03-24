package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="prontuarios")
public class Prontuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    private List<Appointment> consultas = new ArrayList<>();
    private List<Prescricao> prescricoes = new ArrayList<>();
    
    public Prontuario() {
    }

    public Prontuario(Paciente paciente){
        this.paciente = paciente;
    }


    
    public String toString(){
        StringBuilder sb = new StringBuilder();

        List<Appointment> consultas = paciente.getAppointments();
        //List<Prescricao> prescricoes = paciente.get
        
        sb.append("================ PRONTUARIO ================\n");
        sb.append("Nome: " + paciente.getNome() + "\n");
        sb.append("CPF: " + paciente.getCpf() + "\n");
        sb.append("Idade: " + paciente.getIdade() + "\n");
        //sb.append("Fator de Risco: " + ??? + "\n");
        sb.append("Convenio: " + paciente.getPlano() + "\n");
        
        for(Appointment consulta : consultas) {
            sb.append(consultas.toString());
            sb.append("----------------------------------\n");
        }

        /*for(Prescricao prescricao : prescricoes) {
            sb.append(prescricao.toString());
            sb.append("\n----------------------------------\n");
        }*/

        System.out.println("============================================");

        return sb.toString();
    }

    public Paciente getPaciente() {
        return null;
    }
}

