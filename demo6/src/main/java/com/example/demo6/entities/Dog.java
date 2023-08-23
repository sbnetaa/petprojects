package com.example.demo6.entities;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nullable;
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
public class Dog extends AbstractEntity {
	//@Transient
	private transient static final String tableName = "dogs";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 3, max = 30, message = "Nickname should be between 3 and 30 characters long")
	private String nickname;
	private String name;
	@Column(columnDefinition = "character varying (10)")
	@Enumerated(EnumType.STRING)	
	@Nullable
	private Gender gender;
	private transient boolean isMale = false;
	@Column(columnDefinition = "character varying (20)")
	@Enumerated(EnumType.STRING)
	@Nullable
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
	private OffsetDateTime modifiedAt;// = LocalDateTime.now().withNano(0);	
	@Column(name = "created_at")
	@CreatedDate
	private OffsetDateTime createdAt;// = OffsetDateTime.now().withNano(0);
	private transient boolean isModifying = false;
	
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
	
	public OffsetDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(OffsetDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
	

	public boolean isModifying() {
		return isModifying;
	}

	public void setModifying(boolean isModifying) {
		this.isModifying = isModifying;
	}
	

	public static String getTablename() {
		return tableName;
	}

	public Dog(Long id,
			@Size(min = 3, max = 30, message = "Nickname should be between 3 and 30 characters long") String nickname,
			String name, Gender gender, Breed breed, LocalDate dateOfBirth, int puppies, OffsetDateTime modifiedAt,
			OffsetDateTime createdAt) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.gender = gender;
		this.breed = breed;
		this.dateOfBirth = dateOfBirth;
		this.puppies = puppies;
		this.modifiedAt = modifiedAt;
		this.createdAt = createdAt;
		
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", nickname=" + nickname + ", name=" + name + ", gender=" + gender + ", breed=" + breed
				+ ", dateOfBirth=" + dateOfBirth + ", puppies=" + puppies + ", modifiedAt=" + modifiedAt
				+ ", createdAt=" + createdAt + ", isModifying=" + isModifying + "]";
	}

	public Dog(){}
	
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
		
		public static Breed getBreedName(String enumType) {
			for (Breed breedName : Breed.values()) {
				if (breedName.getType().equals(enumType)) return breedName;
			}
			return null;
		}
	}
	
	public enum Gender {
		MALE("Мальчик"), 
		FEMALE("Девочка"); 
	
		String type;
		Gender(String type){this.type = type;}
		public String getType(){return type;}
		
		public static Gender getGenderName(String genderType) {
			if (genderType.equals("Мальчик")) return MALE;
			if (genderType.equals("Девочка")) return FEMALE;
			return null;
		}
		
	}
}
