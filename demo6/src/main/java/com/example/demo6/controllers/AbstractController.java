package com.example.demo6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo6.entities.AbstractEntity;
import com.example.demo6.service.EntityService;

public abstract class AbstractController<E extends AbstractEntity, S extends EntityService<E>> implements EntityController<E> {
	private S service;

	@Autowired
	public AbstractController(S service) {
		//super();
		this.service = service;
	}
	
	
}
