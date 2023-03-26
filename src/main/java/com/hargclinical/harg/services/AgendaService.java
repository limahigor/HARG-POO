package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Agenda;
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
		return obj.get();
	}

    public Agenda create(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public void delete(Long id) {
        agendaRepository.deleteById(id);
    }
}