public class ServProcedimento extends Services {
    private String procedimento;

    public ServProcedimento(String nome, double valor, String procedimento) {
        super(nome, valor);
        this.procedimento = procedimento;
    }

    public String getTipo() {
        return procedimento;
    }

    @Override
    public void realizarProcedimento() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do procedimento: ");
        nome = sc.nextLine();

        System.out.print("Nome da especialidade: ");
        especialidade = sc.nextLine();

        System.out.print("Valor do procedimento: ");
        valor = sc.nextDouble();
    }
}