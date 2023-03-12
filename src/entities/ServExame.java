import java.util.Scanner;

public class ServExame extends Services {

    public ServExame(String nome, String especialidade, double valor) {
        super(nome, especialidade, valor);
    }

    public void cadastrarExame() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome o nome do exame: ");
        nome = sc.nextLine();

        System.out.print("Nome da especialidade: ");
        especialidade = sc.nextLine();

        System.out.print("Valor do exame: ");
        valor = sc.nextDouble();
        sc.close();
    }
}