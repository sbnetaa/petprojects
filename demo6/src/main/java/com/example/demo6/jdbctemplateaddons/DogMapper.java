package com.example.demo6.jdbctemplateaddons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo6.entities.Dog;

public class DogMapper implements RowMapper<Dog> {

	@Override
	public Dog mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dog dog = new Dog();
		dog.setId((long) rs.getInt("id"));
        dog.setNickname(rs.getString("nickname"));
        dog.setName(rs.getString("name"));
        dog.setDateOfBirth((LocalDate) rs.getObject("date_of_birth", LocalDate.class));
        dog.setBreed(Dog.Breed.valueOf(rs.getString("breed")));
        dog.setGender(Dog.Gender.valueOf(rs.getString("gender")));
        dog.setPuppies(rs.getInt("puppies_count"));
        dog.setCreatedAt((OffsetDateTime) rs.getObject("created_at", OffsetDateTime.class));
        dog.setModifiedAt((OffsetDateTime) rs.getObject("modified_at", OffsetDateTime.class));
		
			
		return dog;
	}

}
