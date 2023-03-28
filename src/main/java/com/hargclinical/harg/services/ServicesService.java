package com.hargclinical.harg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.repositories.ServicesRepository;

@Service
public class ServicesService {

	@Autowired
	private ServicesRepository repository;

	public List<Services> findAll() {
		return repository.findAll();
	}

	public Services findById(Long id) {
		Optional<Services> obj = repository.findById(id);
		return obj.get();
	}

    public Services insert(Services obj){
        return repository.save(obj);
    }
}