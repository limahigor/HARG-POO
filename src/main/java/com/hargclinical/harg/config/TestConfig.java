package com.hargclinical.harg.config;

import com.hargclinical.harg.repositories.AgendaRepository;
import com.hargclinical.harg.repositories.AppointmentRepository;
import com.hargclinical.harg.repositories.CaixaRepository;
import com.hargclinical.harg.repositories.MedicamentoPrescritoRepository;
import com.hargclinical.harg.repositories.MedicoRepository;
import com.hargclinical.harg.repositories.PacienteRepository;
import com.hargclinical.harg.repositories.PrescricaoRepository;
import com.hargclinical.harg.repositories.ServicesRepository;
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.CaixaService;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.services.ServicesService;
import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Caixa;
import com.hargclinical.harg.entities.Dias;
import com.hargclinical.harg.entities.MedicamentoPrescrito;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Orcamento;
import com.hargclinical.harg.entities.OrcamentoMedicamentos;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.Prescricao;
import com.hargclinical.harg.entities.ServConsulta;
import com.hargclinical.harg.entities.ServExame;
import com.hargclinical.harg.entities.ServProcedimento;
import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.entities_enums.Plano;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.ListCrudRepository;

@Configuration
public class TestConfig implements CommandLineRunner{

    private static final String Services = null;

    @Autowired
    private ServicesRepository serviceRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private MedicamentoPrescritoRepository medicamentoPrescritoRepository;

    @Autowired
    private ServicesService serviceService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private CaixaService caixaService;


    @Override
    public void run(String... args) throws Exception {

        try{
            // Agenda novaAgenda = agendaService.findById(1L);

            // if(novaAgenda == null){
            //     novaAgenda = new Agenda();

            //     List<Dias> dias = new ArrayList<>();
            //     novaAgenda.setListaDias(dias, novaAgenda);
            //     novaAgenda.setDias(dias);
            //     agendaService.create(novaAgenda);
            // }

            //Medico medico = new Medico("Manoel Gomes", "07229421500", LocalDate.of(1980, 2, 1), 'M', "Dermatologia", "05625206056");
            Paciente paciente1 = new Paciente();
            paciente1.setNome("Anderson");
            // pacienteService.insert(paciente1);
            // Services servico = new Services("Botox", "Dermatologia", 285.00);
            // Appointment consulta = new Appointment(medico, paciente1, servico, LocalDate.of(2023, 5, 1), LocalTime.of(14, 30));
            
            // Agenda medicoAgenda = new Agenda();
            // List<Dias> diasMedico = new ArrayList<>();
            // medicoAgenda.setListaDias(diasMedico, medicoAgenda);
            // medicoAgenda.setDias(diasMedico);
            // medicoAgenda.setMedico(medico);
            // medico.setAgenda(medicoAgenda);
            // agendaService.create(medicoAgenda);
            // medicoService.insert(medico);
            
            // Agenda servicoAgenda = new Agenda();
            // List<Dias> diasServico = new ArrayList<>();
            // servicoAgenda.setListaDias(diasServico, servicoAgenda);
            // servicoAgenda.setDias(diasServico);
            // servicoAgenda.setService(servico);
            // servico.setAgenda(servicoAgenda);
            // agendaService.create(servicoAgenda);

            // servicoAgenda.agendarConsulta(consulta);
            // medicoAgenda.agendarConsulta(consulta);
            // novaAgenda.agendarConsulta(consulta);

            // appointmentRepository.save(consulta);
            

            caixaService.abrirCaixa();

            // List<Caixa> caixas = new ArrayList<>();

            // Caixa caixa = caixas.get(0);
            System.out.println("11111111111111111=======================================================================================");
            // Prescricao prescricao = new Prescricao();
            // OrcamentoMedicamentos orcamento = new OrcamentoMedicamentos(paciente1, prescricao);
            
            // orcamento.setOrcamento(500);
            // caixa.addMovimentacoesCaixa(orcamento);
            

        }catch(Exception e){
            System.out.println("TESTE ERROR");
            System.out.println(e.getMessage());
        }

    }
    
}
