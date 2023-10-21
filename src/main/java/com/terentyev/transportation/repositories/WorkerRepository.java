package com.terentyev.transportation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.terentyev.transportation.entities.Worker;


@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
	Optional<Worker> findByUsername(String username);
	Worker findByEmail(String email);
	Optional<Worker> findById(Long id);
	@Query("SELECT d FROM Worker d WHERE d.boss = false")
	List<Worker> findAllDrivers();
}
