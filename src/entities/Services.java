package entities;

import java.util.ArrayList;
import java.util.List;

public class Services {
    public String nome;
    public List<String> profissionais = new ArrayList<>();
    public double valor;

    public Services() {
        this.profissionais = new ArrayList<>();
    }
    
    public void addProfissional(Cadastro profissional) {
        profissionais.add(profissional.nome);
    }
    
    public void printService() {
        System.out.println("Procedimento: " + procedimento);
        if(profissionais.size() != 0){
            System.out.println("Profissionais: ");

            for (String nome : profissionais) {
                System.out.println(nome);
            } 
        }
        System.out.println("Valor: R$" + valor + "\n");
    }

    public static Services buscarProcedimentoNome(List<Services> allServices, String x){
        for(Services search : allServices) {
            if (search.procedimento.equals(x)) {
                return search;
            }
        }
        return null;
    }

    public static Services buscarProcedimentoEsp(List<Services> allServices, String x){
        for(Services search : allServices) {
            if (search.especialidade.equals(x)) {
                return search;
            }
        }
        return null;
    }
    
    public boolean verificarProfissional(String nomeMedico) {

        if(profissionais.equals(nomeMedico)){
            return true;
        }
        
        return false;
    }
}

