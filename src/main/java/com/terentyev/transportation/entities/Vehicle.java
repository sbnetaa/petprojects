package com.terentyev.transportation.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	private static transient boolean displayTrailers = false;
	private static int commonFuelConsumptionRate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "boolean default false")
	private boolean trailer;
	@Column(columnDefinition = "character varying (30)")
	private String make;
	@ManyToOne
	private Type type;
	@Column(columnDefinition = "character varying (30)")	
	private String model;
	@Column(columnDefinition = "character varying (20)")
	private String number;
	private Long remainingFuel;
	private Long speedometerReading;
	@Column(columnDefinition = "boolean default false")
	private boolean individualFcr;
	private Integer fuelConsumptionRate;

	
	public Vehicle(){}
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public boolean isTrailer() {
		return trailer;
	}



	public void setTrailer(boolean trailer) {
		this.trailer = trailer;
	}



	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}


	public static boolean isDisplayTrailers() {
		return displayTrailers;
	}


	public static void setDisplayTrailers(boolean displayTrailers) {
		Vehicle.displayTrailers = displayTrailers;
	}



	public Long getRemainingFuel() {
		return remainingFuel;
	}


	public void setRemainingFuel(Long remainingFuel) {
		this.remainingFuel = remainingFuel;
	}


	public Long getSpeedometerReading() {
		return speedometerReading;
	}


	public void setSpeedometerReading(Long speedometerReading) {
		this.speedometerReading = speedometerReading;
	}



	public boolean isIndividualFcr() {
		return individualFcr;
	}


	public void setIndividualFcr(boolean individualFcr) {
		this.individualFcr = individualFcr;
	}


	public Integer getFuelConsumptionRate() {
		//if (!individualFcr)	return commonFuelConsumptionRate;
		return fuelConsumptionRate;
	}


	public void setFuelConsumptionRate(Integer fuelConsumptionRate) {
		this.fuelConsumptionRate = fuelConsumptionRate;
	}


	public static int getCommonFuelConsumptionRate() {
		return commonFuelConsumptionRate;
	}


	public static void setCommonFuelConsumptionRate(int commonFuelConsumptionRate) {
		Vehicle.commonFuelConsumptionRate = commonFuelConsumptionRate;
	}


	@Entity
	@Table(name = "trailer_types")
	public static class Type {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		@OneToMany
		private List<Vehicle> trucks;
		
		
		public Type(){}

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

		public List<Vehicle> getTrucks() {
			return trucks;
		}

		public void setTrucks(List<Vehicle> trucks) {
			this.trucks = trucks;
		}			
		
		
	}
}
