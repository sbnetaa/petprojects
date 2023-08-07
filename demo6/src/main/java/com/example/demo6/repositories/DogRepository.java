package com.example.demo6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo6.entities.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {}
