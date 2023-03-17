package entities;

import java.util.ArrayList;
import java.util.List;

public class Prescricao {
    public List<String> nomes;
    protected List<Integer> intervalos;
    protected List<Double> valores;

    public Prescricao (){
        this.nomes = new ArrayList<>();
        this.intervalos = new ArrayList<>();
        this.valores = new ArrayList<>();
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
