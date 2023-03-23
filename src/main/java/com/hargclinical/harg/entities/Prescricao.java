package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="prescricoes")
public class Prescricao implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public List<String> nomes = new ArrayList<>();
    protected List<Integer> intervalos = new ArrayList<>();
    protected List<Double> valores = new ArrayList<>();

    @OneToOne(mappedBy = "orcamentoMedicamentos", cascade = CascadeType.ALL)
    private OrcamentoMedicamentos orcamentoMedicamentos;

    public Prescricao (){

    }

    public void addPrescricao(String medicamento, int intervalo, double valor){
        nomes.add(medicamento);
        intervalos.add(intervalo);
        valores.add(valor);
    }

    public void printPrescricao(){
        for (int i = 0; i < nomes.size(); i++) {
            String medicamento = nomes.get(i);
            Integer intervalo = intervalos.get(i);
            System.out.println(medicamento + ": de " + intervalo + " em " + intervalo + " horas.");
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nomes.size(); i++){
            String aux = String.format(nomes.get(i) + ": de " + intervalos.get(i) + " em " + intervalos.get(i) + " horas.\n");
            sb.append(aux);
        }
        return sb.toString();
    }

    public void clearPrescricao(){
        nomes.clear();
        intervalos.clear();
        valores.clear();
    }
    
}
