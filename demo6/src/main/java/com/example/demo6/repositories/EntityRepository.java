package com.example.demo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo6.entities.AbstractEntity;

@NoRepositoryBean
public interface EntityRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {}
