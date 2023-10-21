package com.terentyev.transportation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terentyev.transportation.entities.Waybill;
import com.terentyev.transportation.entities.Worker;
import com.terentyev.transportation.repositories.WaybillRepository;

@Service
public class DriverService {
	private WaybillRepository waybillRepository;

	@Autowired
	public DriverService(WaybillRepository waybillRepository) {
		super();
		this.waybillRepository = waybillRepository;
	}
	
	public List<Waybill> findAllWaybills(Worker driver){
		return waybillRepository.findDriversWaybills(driver);
	}
}
