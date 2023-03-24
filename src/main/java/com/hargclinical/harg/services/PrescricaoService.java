package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Prescricao;
import com.hargclinical.harg.repositories.PrescricaoRepository;

@Service
public class PrescricaoService {

	@Autowired
	private PrescricaoRepository repository;

	public List<Prescricao> findAll() {
		return repository.findAll();
	}

	public Prescricao findById(Long id) {
		Optional<Prescricao> obj = repository.findById(id);
		return obj.get();
	}
}