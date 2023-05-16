package com.hargclinical.harg.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.ServConsulta;
import com.hargclinical.harg.entities.ServExame;
import com.hargclinical.harg.entities.ServProcedimento;
import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.repositories.AppointmentRepository;
import com.hargclinical.harg.services.exceptions.IllegalArgument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private AgendaService agendaService;

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public Appointment findById(@PathVariable Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        return optionalAppointment.orElse(null);
    }

    public void save(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void deleteById(Long id){
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> returnConsultas(List<Appointment> consultas, String servico) {
        List<Appointment> consultasRetorno = new ArrayList<>();

        if(servico.equals("procedimento")) {
            for(Appointment consulta : consultas) {
                if(consulta.getService() instanceof ServProcedimento) {
                    consultasRetorno.add(consulta);
                }
            }

        } else if (servico.equals("consulta")) {
            for(Appointment consulta : consultas) {
                if(consulta.getService() instanceof ServConsulta) {
                    consultasRetorno.add(consulta);
                }
            }

        } else if(servico.equals("exame")) {
            for(Appointment consulta : consultas) {
                if(consulta.getService() instanceof ServExame) {
                    consultasRetorno.add(consulta);
                }
            }

        } else {
            return null;
        }
        return consultasRetorno;
    }

    public Appointment agendamento(String jsonData) {
        ObjectMapper mapper = new ObjectMapper();
        Appointment newAppointment = null;

        try{
            JsonNode node = mapper.readTree(jsonData);
            
            String cpf = node.get("cpf").asText();
            String data = node.get("data").asText();
            LocalDate date = LocalDate.parse(data);
            String hora = node.get("hora").asText();
            LocalTime horario = LocalTime.parse(hora);
            Long serviceId = node.get("procedimento").asLong();
            Long medicoId = node.get("medico").asLong();

            for(Appointment consulta : medicoService.findById(medicoId).getAppointments()) {
                if(consulta.getData().isEqual(date) && consulta.getHorario().equals(horario)) {
                    throw new IllegalArgument("Horário já agendado.");
                }
            }

            for(Appointment consulta : pacienteService.findByCpfContaining(cpf).get(0).getProntuario().getAppointments()) {
                if(consulta.getData().isEqual(date) && consulta.getHorario().equals(horario)) {
                    throw new IllegalArgument("Horário já agendado.");
                }
            }

            if(cpf.equals(medicoService.findById(medicoId).getCpf())) {
                throw new IllegalArgument("Médico não pode fazer uma consulta com ele mesmo.");
            }

            if(date.isBefore(LocalDate.now())) {
                throw new IllegalArgument("Data inválida.");
            }

            if(horario.isBefore(LocalTime.now()) && date.isEqual(LocalDate.now())) {
                throw new IllegalArgument("Horário inválido.");
            }

            Medico medico = medicoService.findById(medicoId);
            Services service = servicesService.findById(serviceId);
            Paciente paciente = pacienteService.findByCpfContaining(cpf).get(0);
            
            newAppointment = new Appointment(medico, paciente.getProntuario(), service, date, horario);
            
            Agenda geralAgenda = agendaService.findById(1L);
            Agenda medicoAgenda = medico.getAgenda();
            Agenda serviceAgenda = service.getAgenda();
            
            medicoAgenda.agendarConsulta(newAppointment);
            serviceAgenda.agendarConsulta(newAppointment);
            geralAgenda.agendarConsulta(newAppointment);

            save(newAppointment);
            return newAppointment;

        } catch (IllegalArgument e) { 
            System.out.println("ERROR: " + e.getMessage());
            throw new IllegalArgument(e.getMessage());

        } catch (Exception e) {
            System.out.println("ERROR: "+ e.getMessage());
            throw new IllegalArgument("Erro ao agendar!");
        }
    }

}