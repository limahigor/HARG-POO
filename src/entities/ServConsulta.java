import java.util.Scanner;

public class ServConsulta extends Services {

    public ServConsulta(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

    public void cadastrarConsulta() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome o tipo da consulta: ");
        nome = sc.nextLine();

        System.out.print("Nome da especialidade: ");
        especialidade = sc.nextLine();

        System.out.print("Valor da Consulta: ");
        valor = sc.nextDouble();
        sc.close();
    }
}