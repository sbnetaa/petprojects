package com.example.demo6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo6.entities.Dog;
import com.example.demo6.repositories.DogRepository;

@Service
@Transactional(readOnly = true)
public class DogService {
	private DogRepository dogRepository;
	
	@Autowired
	public DogService(DogRepository dogRepository) {
		this.dogRepository = dogRepository;
	}

	public DogRepository getDogRepository() {
		return dogRepository;
	}

	public void setDogRepository(DogRepository dogRepository) {
		this.dogRepository = dogRepository;
	}


	public List<Dog> findAll() {
	 	return dogRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void save(Dog dog) {
		dogRepository.save(dog);		
	}
}
