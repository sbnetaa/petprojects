package com.terentyev.transportation.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "waybills")
public class Waybill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long number;
	@ManyToOne
	private Company company;
	@ManyToOne
	private Worker driver;
	@ManyToOne
	@Nullable
	private Vehicle car;
	@ManyToOne
	private Vehicle trailer;
	private LocalDate dateOfTravel;
	private LocalDateTime timeOfDeparture;
	private LocalDateTime timeOfReturn;
	private Long speedometerBefore;
	private Long fuelBefore;
	private Long engineHours;
	private Long fuelInRefrigerationUnit;
	private Long speedometerAfter;
	private Long fuelAfter;
	private Status status;
	
	public Waybill(){}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getNumber() {
		return number;
	}



	public void setNumber(Long number) {
		this.number = number;
	}



	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Worker getDriver() {
		return driver;
	}

	public void setDriver(Worker driver) {
		this.driver = driver;
	}

	public Vehicle getCar() {
		return car;
	}

	public void setCar(Vehicle car) {
		this.car = car;
	}

	public Vehicle getTrailer() {
		return trailer;
	}

	public void setTrailer(Vehicle trailer) {
		this.trailer = trailer;
	}

	public LocalDate getDateOfTravel() {
		return dateOfTravel;
	}

	public void setDateOfTravel(LocalDate dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}

	public LocalDateTime getTimeOfDeparture() {
		return timeOfDeparture;
	}

	public void setTimeOfDeparture(LocalDateTime timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}

	public LocalDateTime getTimeOfReturn() {
		return timeOfReturn;
	}

	public void setTimeOfReturn(LocalDateTime timeOfReturn) {
		this.timeOfReturn = timeOfReturn;
	}



	public Long getSpeedometerBefore() {
		return speedometerBefore;
	}



	public void setSpeedometerBefore(Long speedometerBefore) {
		this.speedometerBefore = speedometerBefore;
	}



	public Long getFuelBefore() {
		return fuelBefore;
	}



	public void setFuelBefore(Long fuelBefore) {
		this.fuelBefore = fuelBefore;
	}



	public Long getEngineHours() {
		return engineHours;
	}

	public void setEngineHours(Long engineHours) {
		this.engineHours = engineHours;
	}

	
	public Long getFuelInRefrigerationUnit() {
		return fuelInRefrigerationUnit;
	}



	public void setFuelInRefrigerationUnit(Long fuelInRefrigerationUnit) {
		this.fuelInRefrigerationUnit = fuelInRefrigerationUnit;
	}

	

	public Long getSpeedometerAfter() {
		return speedometerAfter;
	}



	public void setSpeedometerAfter(Long speedometerAfter) {
		this.speedometerAfter = speedometerAfter;
	}



	public Long getFuelAfter() {
		return fuelAfter;
	}



	public void setFuelAfter(Long fuelAfter) {
		this.fuelAfter = fuelAfter;
	}
	
	

	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public enum Status{
		ACTIVE("Активен"), SELECTED("Выбран"), CLOSED("Закрыт");
		
		private String description;
		
		Status(String description){
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public Status getName(String description) {
			for (Status status : Status.values()) {
				if (status.getDescription().equals(description)) return status;
			}
			return null;
		}
		
	}

	@Entity
	@Table(name = "companies")
	public static class Company{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private String legalAddress;
		private Long tin;
		private Long ogrn;
		private Long rrc;
		private Long okpo;
		private Long okved;
		private Long okato;
		
		public Company(){}
		
		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLegalAddress() {
			return legalAddress;
		}

		public void setLegalAddress(String legalAddress) {
			this.legalAddress = legalAddress;
		}

		public Long getTin() {
			return tin;
		}

		public void setTin(Long tin) {
			this.tin = tin;
		}

		public Long getOgrn() {
			return ogrn;
		}

		public void setOgrn(Long ogrn) {
			this.ogrn = ogrn;
		}

		public Long getRrc() {
			return rrc;
		}

		public void setRrc(Long rrc) {
			this.rrc = rrc;
		}

		public Long getOkpo() {
			return okpo;
		}

		public void setOkpo(Long okpo) {
			this.okpo = okpo;
		}

		public Long getOkved() {
			return okved;
		}

		public void setOkved(Long okved) {
			this.okved = okved;
		}

		public Long getOkato() {
			return okato;
		}

		public void setOkato(Long okato) {
			this.okato = okato;
		}
		
		
	}
}
