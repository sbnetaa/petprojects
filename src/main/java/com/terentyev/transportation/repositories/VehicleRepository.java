package com.terentyev.transportation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.terentyev.transportation.entities.Vehicle;

//import jakarta.transaction.Transactional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	@Query("SELECT t FROM Vehicle t WHERE t.trailer = ?1")
	List<Vehicle> findAllVehicles(boolean searchForTrailers);
	
	@Modifying
	@Transactional(readOnly = false)
	@Query("UPDATE Vehicle v SET v.fuelConsumptionRate = ?1 WHERE v.individualFcr = false")
	void saveCommonFuelConsumptionRate(int fcr);

}
