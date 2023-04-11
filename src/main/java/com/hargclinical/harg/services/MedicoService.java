package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import com.hargclinical.harg.entities.Comorbidades;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.repositories.MedicoRepository;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	public List<Medico> findAll() {
		return repository.findAll();
	}

	public Medico findById(Long id) {
		Optional<Medico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

    public List<Medico> findByNameContaining(String nome){
        return repository.findByNomeContaining(nome);
    }

    public List<Medico> findByCrmContaining(String nome) {
        return repository.findByCrmContaining(nome);
    }

	public Medico insert(Medico obj){
        return repository.save(obj);
    }

	public ModelAndView getModelAndView(Medico medico, ModelAndView viewPage) {
		viewPage.addObject("nome", medico.getNome());
		viewPage.addObject("crm", medico.getCrm());
		viewPage.addObject("especializacao", medico.getEspecializacao());

		return viewPage;
	}
}