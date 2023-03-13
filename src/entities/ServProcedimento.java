package entities;

import java.util.Scanner;

public class ServProcedimento extends Services {

    public ServProcedimento(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

    public void cadastrarProcedimento() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do procedimento: ");
        nome = sc.nextLine();

        System.out.print("Nome da especialidade: ");
        especialidade = sc.nextLine();

        System.out.print("Valor do procedimento: ");
        valor = sc.nextDouble();
        sc.close();
    }

    
}