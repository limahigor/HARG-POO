package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.services.MedicoService;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoResource{

    @Autowired
	private MedicoService service;

    @GetMapping
	public ResponseEntity<List<Medico>> findAll() {
		List<Medico> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Medico> findById(@PathVariable Long id) {
		Medico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}