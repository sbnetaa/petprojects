package com.example.demo6.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	@Basic
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@Column(name = "puppies_count")
	private int puppies;
	@Column(name = "modified_at")
	@LastModifiedDate
	private LocalDateTime modifiedAt = LocalDateTime.now().withNano(0);	
	@Column (name = "created_at")
	@CreatedDate
	private LocalDateTime createdAt = LocalDateTime.now().withNano(0);
	
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPuppies() {
		return puppies;
	}

	public void setPuppies(int puppies) {
		this.puppies = puppies;
	}	
	
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
