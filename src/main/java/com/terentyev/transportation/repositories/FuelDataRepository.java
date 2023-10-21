package com.terentyev.transportation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.terentyev.transportation.entities.FuelData;

@Repository
public interface FuelDataRepository extends JpaRepository<FuelData, Integer> {
	
	@Query("SELECT f FROM FuelData f WHERE f.start = (SELECT MIN(f2.start) FROM FuelData f2 WHERE f2.start > CURRENT_DATE)")
	FuelData findNextInterval();
	@Query("SELECT f FROM FuelData f WHERE f.start = (SELECT MAX(f2.start) FROM FuelData f2 WHERE f2.start <= CURRENT_DATE)")
	FuelData findCurrentInterval();

}
