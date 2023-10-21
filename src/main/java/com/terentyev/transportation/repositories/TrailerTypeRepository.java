package com.terentyev.transportation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terentyev.transportation.entities.Vehicle.Type;

@Repository
public interface TrailerTypeRepository extends JpaRepository<Type, Long> {

}
