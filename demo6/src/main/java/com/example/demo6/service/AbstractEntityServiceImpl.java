package com.example.demo6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo6.entities.AbstractEntity;
import com.example.demo6.repositories.EntityRepository;


@Transactional(readOnly = true)
public abstract class AbstractEntityServiceImpl<E extends AbstractEntity, R extends EntityRepository<E>> implements EntityService<E> {
	private R repository;
	
	
	@Autowired
	public AbstractEntityServiceImpl(R repository) {
		this.repository = repository;
	}



	public List<E> findAll() {
	 	return repository.findAll();
	}
	
	@Transactional(readOnly = false)
	public E save(E entity) {
		repository.save(entity);
		return entity;
	}
}
