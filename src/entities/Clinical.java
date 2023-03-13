package entities;

import java.util.ArrayList;
import java.util.List;

public class Clinical {
    public Caixa caixa;
    public List<Pessoa> listaMedico;
    public List<Pessoa> listaPaciente;
    public List<Orcamento> listaOrcamentos;
    public List<Appointment> listaConsultas;
    public List<Services> listaProcedimentos;
        
    public Clinical(){
        this.listaMedico = new ArrayList<>();
        this.listaPaciente = new ArrayList<>();
        this.listaConsultas = new ArrayList<>();
        this.listaOrcamentos = new ArrayList<>();
        this.listaProcedimentos = new ArrayList<>();
    }

    public void createPatient(String name, String cpf, int idade, char sexo, int plano_saude,
                              boolean tabagismo, boolean obesidade, boolean hipertensao, boolean gestante,
                              boolean diabetes){
        
        Prioridade prioridade = new Prioridade(idade, tabagismo, obesidade,
                                               hipertensao, gestante, diabetes);
        prioridade.setPrioridade();

        Pessoa paciente = new Paciente(name, cpf, idade, sexo, plano_saude, prioridade);

        listaPaciente.add(paciente);
        System.out.println(paciente);
    }

    public void createDoctor(String name, char sexo, String especializacao, String cpf, int idade, String crm){
        Pessoa doctor = new Medico(name, cpf, idade, sexo, crm, especializacao);
        
        Services procedimento = Services.buscarProcedimentoEsp(this.listaProcedimentos, especializacao);

        if(procedimento != null){
            procedimento.addProfissional(doctor);
        }

        listaMedico.add(doctor);
    }

    public void createService(String procedimento, String especialidade, double valor){
        Services service = new Services(procedimento, especialidade, valor);

        for(Pessoa cadastro : this.listaMedico) {
            if(cadastro.especializacao.equals(especialidade)){
                service.addProfissional(cadastro);
            }
        }

        listaProcedimentos.add(service);
        System.out.println("Procedimento criado com sucesso!!\n");
        service.printService();
    }

    public String pegarNomeCadastro(String cpf){
        Pessoa pessoa = Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);
        if(pessoa == null){
            return null;
        }

        return pessoa.nome;
    }

    public boolean verificarPessoa(String cpf){
        Pessoa pessoa = Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);
        if(pessoa == null){
            return false;
        }

        return true;
    }

    public boolean verificarProcedimento(String nome){
        Services procedimento = Services.buscarProcedimentoNome(this.listaProcedimentos, nome);

        if(procedimento != null){
            return true;
        }else{
            return false;
        }
    }

    public void printService(String nome){
        Services procedimento = Services.buscarProcedimentoNome(this.listaProcedimentos, nome);

        procedimento.printService();
    }

    public boolean verProfissionalService(String nomeMedico, String nomeProcedimento){
        Services procedimento = Services.buscarProcedimentoNome(this.listaProcedimentos, nomeProcedimento);

        if(procedimento.verificarProfissional(nomeMedico)){
            return true;
        }else{
            return false;
        }
    }

    public String pegarIdadeCadastro(String cpf){
        Pessoa pessoa = Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);
        if(pessoa == null){
            return null;
        }

        return Integer.toString(pessoa.idade);
    }

    public String agendarConsulta(String cpfPaciente, String nomeMedico, String nomeProcedimento,
                                int dia, int mes, int ano, int hora, int minuto){
                                    
        Appointment consulta = new Appointment(cpfPaciente, nomeMedico, nomeProcedimento,
                                               dia, mes, ano, hora, minuto);

        Pessoa pessoa = Pessoa.buscarCadastroCPF(this.listaPaciente, cpfPaciente);

        pessoa.prontuario.consultas.add(consulta);
        this.listaConsultas.add(consulta);

        return consulta.toString(this, cpfPaciente);
    }

    public String getMedicalRecord(String cpf){
        Pessoa pessoa = Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);

        return pessoa.prontuario.toString(this, cpf);
    }

    public Prescricao criarPrescricao(String cpf){
        Prescricao prescricao = new Prescricao();
        Pessoa pessoa = Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);

        pessoa.addPrescricao(prescricao);

        return prescricao;
    }

    public String exibirAgenda(int dia, int mes, int ano, int op){
        String agenda;

        if(op == 1){
            agenda = Agenda.agendaPorProfissional(listaConsultas, dia, mes, ano);
        }else if(op == 2){
            agenda = Agenda.agendaPorProcedimento(listaConsultas, dia, mes, ano);
        }else{
            return "Opcao invalida!";
        }
        
        return agenda;
    }
    public void fecharCaixa(){
        this.caixa.clearList(this.listaOrcamentos);
    }
    
    public void abrirCaixa(){
        this.caixa = new Caixa();
    }

    public Orcamento criarOrcamento(String cpf){
        Paciente pessoa = (Paciente)Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);

        Orcamento orcamento = new Orcamento(pessoa);

        this.listaOrcamentos.add(orcamento);

        return orcamento;
    }

    public void attCaixa(Orcamento orcamento){
        this.caixa.addCaixa(orcamento);
    }

    public void alterarConvenio(String cpf, int nivel){
        Paciente pessoa = (Paciente)Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);

        pessoa.plano_saude = nivel;
    }

    public int pegarFator(String cpf){
        Paciente pessoa = (Paciente)Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);

        return pessoa.getFator();
    }

    public int pegarConvenio(String cpf){
        Paciente pessoa = (Paciente)Pessoa.buscarCadastroCPF(this.listaPaciente, cpf);

        return pessoa.plano_saude;
    }
}
