package com.example.demo6.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo6.entities.Dog;

@Repository
public interface DogRepository extends EntityRepository<Dog> {

	List<Dog> findAll();
	Optional<Dog> findById(Long id);
	<D extends Dog> D save(D updatedDog);
	void deleteById(Long id);
}
