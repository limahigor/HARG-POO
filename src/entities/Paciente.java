public class Paciente extends Pessoa {
    protected int plano_saude;
    private Prioridade prioridade;
    private Prontuario prontuario;
    
    public Paciente(String nome, String cpf, int idade, char sexo, int plano_saude, Prioridade prioridade) {
        super(nome, cpf, idade, sexo);
        this.prioridade = prioridade;
        this.plano_saude = plano_saude;
        this.prontuario = new Prontuario();
    }

    public String toString(){

        return "Nome: " + nome + "\n" + 
               "Idade: " + idade + "\n" + 
               "Sexo: " + sexo + "\n" + 
               "CPF: " + cpf + "\n" +
               "Convênio: " + plano_saude + "\n" +
               "Fator de Risco: " + prioridade.getFator();
    }
    
    public void addPrescricao(Prescricao prescricao){
        this.prontuario.prescricoes.add(prescricao);
    }
}
