package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Dias;
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

    public List<Appointment> getConsultasDias(List<Appointment> consultas, Agenda agenda){
        for(Dias dia : agenda.getDias()){
            if(!dia.getConsultas().isEmpty()){
                consultas.addAll(dia.getConsultas());
            }
        }
        return consultas;
    }
    
}