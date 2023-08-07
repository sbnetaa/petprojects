package com.example.demo6.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "dogs")
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id")
	private Long id;
	//@Column(name = "nickname")
	private String nickname;
	//@Column(name = "name")
	private String name;
	//@Column(name = "breed")
	private String breed;
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

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
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

}
