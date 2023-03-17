package entities;

import java.util.ArrayList;
import java.util.List;

public class Services {
    public String nome;
    public String especialidade;
    public List<String> profissionais = new ArrayList<>();
    public double valor;

    public Services() {
        this.profissionais = new ArrayList<>();
    }

    public Services(String nome, String especialidade, double valor) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.valor = valor;
    }

    public void addProfissional(Pessoa profissional) {
        profissionais.add(profissional.nome);
    }

    public boolean verificarProfissional(String nomeMedico) {

        if(profissionais.contains(nomeMedico)) {
            return true;
        }else{
            return false;
        }

    }

    public void printService() {
        System.out.println("Procedimento: " + nome);

        if (profissionais.size() != 0) {
            System.out.println("Profissionais: ");

            for(String nome : profissionais) {
                    System.out.println(nome);
            }
        }

        System.out.println("Valor: R$" + valor + "\n");
    }

    public static Services buscarProcedimentoNome(List<Services> allServices, String nome) {
         for (Services search : allServices) {
             if (search.nome.equals(nome)) {
                 return search;
             }
         }
         return null;
    }

    public static Services buscarProcedimentoEsp(List<Services> allServices, String x) {
        for (Services search : allServices) {
            if (search.especialidade.equals(x)) {
                return search;
            }
        }
        return null;
    }
}
