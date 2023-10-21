package com.terentyev.transportation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.terentyev.transportation.entities.Waybill;
import com.terentyev.transportation.entities.Worker;

@Repository
public interface WaybillRepository extends JpaRepository<Waybill, Long> {
	
	@Query("SELECT w FROM Waybill w WHERE w.driver = ?1")
	List<Waybill> findDriversWaybills(Worker driver);
}
