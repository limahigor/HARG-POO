package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.repositories.AgendaRepository;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Agenda findById(Long id) {
		Optional<Agenda> obj = agendaRepository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

    public void create(Agenda agenda) {
        agendaRepository.save(agenda);
    }

    public void delete(Long id) {
        agendaRepository.deleteById(id);
    }

    public List<Appointment> findByDay(int dia) {
        Agenda agenda = agendaRepository.findById(1L).get();
        return agenda.getDias().get(dia - 1).getConsultas();
    }

    // public List<Appointment> getConsultasDoDia(LocalDate data) {
    //     return this.agendaRepository.findConsultaByData(data);
    // }
    
    // public Map<Integer, List<Appointment>> getConsultasDoMes(int ano, int mes) {
    //     List<Appointment> consultas = this.agendaRepository.findConsultaByAnoAndMes(ano, mes);
    //     Map<Integer, List<Appointment>> consultasPorDia = new HashMap<>();
    //     for (Appointment consulta : consultas) {
    //         int dia = consulta.getData().getDayOfMonth();
    //         consultasPorDia.computeIfAbsent(dia, k -> new ArrayList<>()).add(consulta);
    //     }
    //     return consultasPorDia;
    // }
    
    // public Map<Integer, Map<Integer, List<Appointment>>> buscarAgenda(int ano) {
    //     Map<Integer, Map<Integer, List<Appointment>>> consultasPorMesEDia = new HashMap<>();
    //     List<Appointment> consultas = this.agendaRepository.findConsultaByAno(ano);
    //     for (Appointment consulta : consultas) {
    //         int mes = consulta.getData().getMonthValue();
    //         int dia = consulta.getData().getDayOfMonth();
    //         consultasPorMesEDia.computeIfAbsent(mes, k -> new HashMap<>())
    //                           .computeIfAbsent(dia, k -> new ArrayList<>())
    //                           .add(consulta);
    //     }
    //     return consultasPorMesEDia;
    // }

}