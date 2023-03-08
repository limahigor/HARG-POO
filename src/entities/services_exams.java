public class ProcedimentoExame extends Services {
    private String procedimento;

    public ProcedimentoExame(String nome, double preco, String procedimento) {
        super(nome, preco);
        this.procedimento = procedimento;
    }

    public String getTipo() {
        return procedimento;
    }

    @Override
    public void realizarProcedimento() {
        
    }
}