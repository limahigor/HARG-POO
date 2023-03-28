package com.hargclinical.harg.config;

import com.hargclinical.harg.repositories.AgendaRepository;
import com.hargclinical.harg.repositories.AppointmentRepository;
import com.hargclinical.harg.repositories.MedicamentoPrescritoRepository;
import com.hargclinical.harg.repositories.MedicoRepository;
import com.hargclinical.harg.repositories.PacienteRepository;
import com.hargclinical.harg.repositories.PrescricaoRepository;
import com.hargclinical.harg.repositories.ServicesRepository;
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.services.ServicesService;
import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Dias;
import com.hargclinical.harg.entities.MedicamentoPrescrito;
import com.hargclinical.harg.entities.Medico;
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

    @Override
    public void run(String... args) throws Exception {

        try{
            Agenda agenda = new Agenda();
            agendaService.create(agenda);
            List<Dias> dias = agenda.getDias();
            for(Dias d : dias) {
                d.setAgenda(agenda);
            }
            Appointment consulta = new Appointment(null, null, null, LocalDate.of(2023, 5, 1), LocalTime.of(14, 30));
            appointmentRepository.save(consulta);
            agenda.agendarConsulta(consulta);
            // Paciente paciente1 = new Paciente("11122233344", "João Silva", 30, 'M', Plano.NENHUM);
            // Paciente paciente2 = new Paciente("22233344455", "Maria Souza", 45, 'F', Plano.SUS);
            // Paciente paciente3 = new Paciente("33344455566", "Pedro Santos", 22, 'M', Plano.PARTICULAR);
            // Paciente paciente4 = new Paciente("44455566677", "Ana Paula Oliveira", 28, 'F', Plano.SUS);
            // Paciente paciente5 = new Paciente("55566677788", "Roberto Alves", 50, 'M', Plano.NENHUM);
            // Paciente paciente6 = new Paciente("66677788899", "Fernanda Costa", 35, 'F', Plano.PARTICULAR);
            // Paciente paciente7 = new Paciente("77788899900", "Lucas Oliveira", 19, 'M', Plano.SUS);
            // Paciente paciente8 = new Paciente("88899900011", "Juliana Fernandes", 42, 'F', Plano.NENHUM);
            // Paciente paciente9 = new Paciente("99900011122", "Gustavo Rodrigues", 27, 'M', Plano.SUS);
            // Paciente paciente10 = new Paciente("00011122233", "Isabela Silva", 33, 'F', Plano.PARTICULAR);

            // Paciente paciente11 = new Paciente("16543122515616", "Manoel Gomes", 36, 'M', Plano.PROFISSIONAL);
            // Paciente paciente12 = new Paciente("1654156415616", "Higor Gomes", 36, 'M', Plano.PROFISSIONAL);
            // Paciente paciente13 = new Paciente("1654116515616", "Guilherme Gomes", 36, 'M', Plano.PROFISSIONAL);
            // Paciente paciente14 = new Paciente("165411515616", "Anderson Silva", 36, 'M', Plano.PROFISSIONAL);
            // Paciente paciente15 = new Paciente("16543515616", "Ana de Kassia Lemos", 36, 'F', Plano.PROFISSIONAL);

            // pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2, paciente3, paciente4, paciente5, paciente6, paciente7, paciente8,
            //                                          paciente9, paciente10, paciente11, paciente12, paciente13, paciente14, paciente15));
            
        }catch(Exception e){
            System.out.println("TESTE ERROR");
            System.out.println(e.getMessage());
        }

    }
    
}
