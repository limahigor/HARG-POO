package entities;

import java.util.Scanner;

public class ServConsulta extends Services {
    private int time;

    public ServConsulta(String nome, String especialidade, double valor, int time) {
        super(nome, especialidade, valor);
        this.time = time;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}