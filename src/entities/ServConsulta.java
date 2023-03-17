package entities;

import java.util.Scanner;

public class ServConsulta extends Services {
    private int time;

    public ServConsulta(String nome, String especialidade, double valor, int time) {
        super(nome, especialidade, valor);
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}