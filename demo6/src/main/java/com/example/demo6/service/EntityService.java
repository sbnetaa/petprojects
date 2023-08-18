package com.example.demo6.service;

import java.util.List;
import java.util.Optional;

import com.example.demo6.entities.AbstractEntity;

public interface EntityService<E extends AbstractEntity> {

	
	List<E> findAll();
	E save(E entity);
}
