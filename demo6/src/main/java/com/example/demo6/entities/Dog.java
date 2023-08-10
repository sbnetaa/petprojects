package com.example.demo6.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "dogs")
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 3, max = 30, message = "Nickname should be between 3 and 30 characters long")
	private String nickname;
	private String name;
	@Column(columnDefinition = "varchar(10)")
	@Enumerated(EnumType.STRING)	
	private Gender gender;
	@Column(columnDefinition = "varchar(30)")
	@Enumerated(EnumType.STRING)
	private Breed breed;
	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;
	@Column(name = "puppies_count")
	private int puppies;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPuppies() {
		return puppies;
	}

	public void setPuppies(int puppies) {
		this.puppies = puppies;
	}

	public Dog() {}
	
	public enum Breed {
		WHWT("Вест Хайленд Вайт Терьер"), 
		SCOTTISH_T("Скотч Терьер"), 
		SEALYHAM_T("Силихем Терьер"), 
		JRT("Джек Рассел Терьер"), 
		AUSTRALIAN_S("Австралийская овчарка"), 
		METIS("Метис/Дворняга"),
		OTHER("Другая");
		
		String type;
		Breed(String type){this.type = type;}
		public String getType(){return type;}
	}
	
	public enum Gender {
		MALE("Мальчик"), 
		FEMALE("Девочка"); 
	
		String type;
		Gender(String type){this.type = type;}
		public String getType(){return type;}
	}
}
