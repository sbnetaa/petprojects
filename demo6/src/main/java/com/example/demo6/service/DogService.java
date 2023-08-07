package com.example.demo6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo6.entities.Dog;
import com.example.demo6.repositories.DogRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class DogService {
	private DogRepository dogRepository;
	
	@Autowired
	public DogService(DogRepository dogRepository) {
		this.dogRepository = dogRepository;
	}


	public List<Dog> findAll() {
	 	return dogRepository.findAll();
	}
}
