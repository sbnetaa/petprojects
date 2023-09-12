package com.example.demo6.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo6.entities.Person;

@Repository
public interface PersonRepository extends EntityRepository<Person> {
	Optional<Person> findByUsername(String username);
	Person findByEmail(String email);
	Optional<Person> findById(Long id);
}
