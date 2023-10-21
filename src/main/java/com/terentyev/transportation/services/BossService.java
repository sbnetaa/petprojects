package com.terentyev.transportation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terentyev.transportation.entities.FuelData;
import com.terentyev.transportation.entities.Vehicle;
import com.terentyev.transportation.entities.Vehicle.Type;
import com.terentyev.transportation.entities.Waybill;
import com.terentyev.transportation.entities.Waybill.Company;
import com.terentyev.transportation.entities.Worker;
import com.terentyev.transportation.repositories.CompanyRepository;
import com.terentyev.transportation.repositories.FuelDataRepository;
import com.terentyev.transportation.repositories.TrailerTypeRepository;
import com.terentyev.transportation.repositories.VehicleRepository;
import com.terentyev.transportation.repositories.WaybillRepository;
import com.terentyev.transportation.repositories.WorkerRepository;

//import jakarta.transaction.Transactional;


@Service
@Transactional(readOnly = true)
public class BossService {
	private VehicleRepository vehicleRepository;
	private TrailerTypeRepository trailerTypeRepository;
	private WorkerRepository workerRepository;
	private WaybillRepository waybillRepository;
	private CompanyRepository companyRepository;
	private FuelDataRepository fuelDataRepository;

	@Autowired
	public BossService(VehicleRepository vehicleRepository
			, TrailerTypeRepository trailerTypeRepository, WorkerRepository workerRepository
			, WaybillRepository waybillRepository, CompanyRepository companyRepository
			, FuelDataRepository fuelDataRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.trailerTypeRepository = trailerTypeRepository;
		this.workerRepository = workerRepository;
		this.waybillRepository = waybillRepository;
		this.companyRepository = companyRepository;
		this.fuelDataRepository = fuelDataRepository;
	}
	
	@Transactional(readOnly = false)
	public List<Vehicle> findAllVehicles(boolean searchForTrailers) {
		vehicleRepository.saveCommonFuelConsumptionRate(fuelDataRepository
				.findCurrentInterval().getConsumptionRate());
		
		/*
		int FcrFromDb = findCurrentFuelData().getConsumptionRate();
		if (Vehicle.getCommonFuelConsumptionRate() != FcrFromDb) {
			Vehicle.setCommonFuelConsumptionRate(FcrFromDb);
			vehicleRepository.saveCommonFuelConsumptionRate(FcrFromDb);
		}
		*/
		return vehicleRepository.findAllVehicles(searchForTrailers);
	}
	
	public List<Type> findAllTrailerTypes() {
		return trailerTypeRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Vehicle addVehicle(Vehicle vehicleToAdd) {
		vehicleToAdd.setTrailer(Vehicle.isDisplayTrailers());
		return vehicleRepository.save(vehicleToAdd);
		
	}
	
	public Vehicle findVehicleById(Long id) {
		return vehicleRepository.findById(id).get();
	}
	
	@Transactional(readOnly = false)
	public Vehicle updateVehicle(Vehicle editedVehicle, Long id) {
		Vehicle oldVehicle = vehicleRepository.findById(id).get();
		oldVehicle.setMake(editedVehicle.getMake());
		oldVehicle.setTrailer(editedVehicle.isTrailer());
		oldVehicle.setType(editedVehicle.getType());
		oldVehicle.setModel(editedVehicle.getModel());
		oldVehicle.setNumber(editedVehicle.getNumber());
		oldVehicle.setIndividualFcr(editedVehicle.isIndividualFcr());
		oldVehicle.setFuelConsumptionRate(editedVehicle.getFuelConsumptionRate());
		if (!oldVehicle.isIndividualFcr()) oldVehicle.setFuelConsumptionRate(Vehicle.getCommonFuelConsumptionRate());	
		return vehicleRepository.save(oldVehicle);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteVehicle(Long id) {
		vehicleRepository.deleteById(id);
	}
	
	
	
	@Transactional(readOnly = false)
	public Type addTrailerType(Type trailerType) {
		return trailerTypeRepository.save(trailerType);
	}
	
	@Transactional(readOnly = false)
	public Type updateTrailerType(Long id, Type updatedType) {
		Type oldType = trailerTypeRepository.findById(id).get();
		oldType.setName(updatedType.getName());
		return trailerTypeRepository.save(oldType);
	}
	
	@Transactional(readOnly = false)
	public void deleteTrailerType(Long id) {
		trailerTypeRepository.deleteById(id);
	}
	
	public List<Worker> findAllDrivers() {
		return workerRepository.findAllDrivers();
		//return workerRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Worker addDriver(Worker driverToAdd) {
		return workerRepository.save(driverToAdd);
		
	}
	
	public Worker findDriverById(Long id) {
		return workerRepository.findById(id).get();
	}
	
	@Transactional(readOnly = false)
	public Worker updateDriver(Worker editedDriver, Long id) {
		Worker oldDriver = workerRepository.findById(id).get();
		oldDriver.setFullName(editedDriver.getFullName());
		oldDriver.setLicenseNumber(editedDriver.getLicenseNumber());
		oldDriver.setCategory(editedDriver.getCategory());
		return workerRepository.save(oldDriver);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteDriver(Long id) {
		workerRepository.deleteById(id);
	}
	
	public List<Waybill> findAllWaybills(){
		return waybillRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Waybill addWaybill(Waybill waybillToAdd) {
		return waybillRepository.save(waybillToAdd);
	}
	
	public Waybill findWaybillById(Long id) {
		return waybillRepository.findById(id).get();
	}
	
	@Transactional(readOnly = false)
	public Waybill updateWaybill(Waybill editedWaybill, Long id) {
		Waybill oldWaybill = waybillRepository.findById(id).get();
		oldWaybill.setNumber(editedWaybill.getNumber());
		//oldWaybill.setCompany(editedWaybill.getCompany());
		oldWaybill.setDriver(editedWaybill.getDriver());
		oldWaybill.setCar(editedWaybill.getCar());
		oldWaybill.setTrailer(editedWaybill.getTrailer());
		oldWaybill.setDateOfTravel(editedWaybill.getDateOfTravel());
		oldWaybill.setTimeOfDeparture(editedWaybill.getTimeOfDeparture());
		oldWaybill.setTimeOfReturn(editedWaybill.getTimeOfReturn());
		oldWaybill.setSpeedometerBefore(editedWaybill.getSpeedometerBefore());
		oldWaybill.setFuelBefore(editedWaybill.getFuelBefore());
		oldWaybill.setEngineHours(editedWaybill.getEngineHours());
		oldWaybill.setFuelInRefrigerationUnit(editedWaybill.getFuelInRefrigerationUnit());
		oldWaybill.setSpeedometerAfter(editedWaybill.getSpeedometerAfter());
		oldWaybill.setFuelAfter(editedWaybill.getFuelAfter());
		if (oldWaybill.getCar() != null) {
		oldWaybill.getCar().setSpeedometerReading(editedWaybill.getSpeedometerAfter());
		oldWaybill.getCar().setRemainingFuel(editedWaybill.getFuelAfter());
		}
		return waybillRepository.save(oldWaybill);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteWaybill(Long id) {
		waybillRepository.deleteById(id);
	}
	
	public List<Company> findAllCompanies(){
		return companyRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Company addCompany(Company companyToAdd) {
		return companyRepository.save(companyToAdd);
	}
	
	public Company findCompanyById(Long id) {
		return companyRepository.findById(id).get();
	}
	
	@Transactional(readOnly = false)
	public Company updateCompany(Company editedCompany, Long id) {
		Company oldCompany = companyRepository.findById(id).get();
		oldCompany.setName(editedCompany.getName());
		oldCompany.setLegalAddress(editedCompany.getLegalAddress());
		oldCompany.setTin(editedCompany.getTin());
		oldCompany.setOgrn(editedCompany.getOgrn());
		oldCompany.setRrc(editedCompany.getRrc());
		oldCompany.setOkpo(editedCompany.getOkpo());
		oldCompany.setOkved(editedCompany.getOkved());
		oldCompany.setOkato(editedCompany.getOkato());
		return companyRepository.save(oldCompany);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteCompany(Long id) {
		companyRepository.deleteById(id);
	}
	
	public List<FuelData> findAllFuelData(){
		return fuelDataRepository.findAll(Sort.by(Sort.Direction.DESC, "start"));
	}
	
	public FuelData findNextFuelData() {
		return fuelDataRepository.findNextInterval();

	}
	
	public FuelData findCurrentFuelData() {
		return fuelDataRepository.findCurrentInterval();
	}
	
	@Transactional(readOnly = false)
	public FuelData addFuelData(FuelData fuelDataToAdd) {
		return fuelDataRepository.save(fuelDataToAdd);
	}
	
	public FuelData findFuelDataById(Integer id) {
		return fuelDataRepository.findById(id).get();
	}
	
	@Transactional(readOnly = false)
	public FuelData updateFuelData(FuelData editedFuelData, Integer id) {
		FuelData oldFuelData = fuelDataRepository.findById(id).get();
		oldFuelData.setStart(editedFuelData.getStart());
		oldFuelData.setConsumptionRate(editedFuelData.getConsumptionRate());
		return fuelDataRepository.save(oldFuelData);
		
	}
	
	@Transactional(readOnly = false)
	public void deleteFuelData(Integer id) {
		fuelDataRepository.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void setCommonFcr(int commonFcr) {
		Vehicle.setCommonFuelConsumptionRate(commonFcr);
	}
}
