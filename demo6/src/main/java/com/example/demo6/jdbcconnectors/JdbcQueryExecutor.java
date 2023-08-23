package com.example.demo6.jdbcconnectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo6.entities.Dog;

import jakarta.annotation.Nullable;

public class JdbcQueryExecutor {
	private static StringBuilder select = new StringBuilder("SELECT * FROM ");
	//private static final String SQL_SELECT =
     //       "SELECT * FROM " + Dog.getTableName() + " WHERE id=? AND nickname LIKE '%?%' AND name LIKE '%?%' AND breed='?' AND gender=";
	private StringBuilder query = new StringBuilder("SELECT * FROM ");
	//private static final Logger LOGGER = Logger.getLogger(JdbcQueryExecutor.class.getName());
	 
	//private static final String;
	
	
	//public List<Dog> find(int id, String nickname, String name, String breed, String)
	
	public JdbcQueryExecutor(){}
	
	public static List<Dog> find(String tableName, Integer id, String nicknameOrNameLike
			, String gender, String breed
			, LocalDate dateOfBirthFrom, LocalDate dateOfBirthTo
			, Integer minPuppiesCount
			, LocalDateTime dateOfCreationFrom, LocalDateTime dateOfCreationTo
			, LocalDateTime dateOfModificationFrom, LocalDateTime dateOfModificationTo
			, String orderBy, boolean descDirection) {
		List<Dog> dogs = new ArrayList<>();
		/*
		JdbcQueryExecutor jdbcqe = new JdbcQueryExecutor().fromTable(tableName).withId(id)
				.withNicknameOrName(nicknameOrNameLike)
				.withBreed(breed).withGender(gender)
				.withPuppiesMoreThan(minPuppiesCount)
				.withDateOfBirthBetween(dateOfBirthFrom, dateOfBirthTo)
				.withDateOfCreationBetween(dateOfCreationFrom, dateOfCreationTo)
				.withDateOfModificationBetween(dateOfModificationFrom, dateOfModificationTo)
				.orderBy(orderBy, ascDirection).replaceLastAnd();
			*/	String message = new JdbcQueryExecutor().fromTable(tableName).withId(id)
           		 .withNicknameOrName(nicknameOrNameLike).withBreed(breed)
           		 .withGender(gender).withPuppiesMoreThan(minPuppiesCount)
           		 .withDateOfBirthBetween(dateOfBirthFrom, dateOfBirthTo)
           		 .withDateOfCreationBetween(dateOfCreationFrom, dateOfCreationTo)
           		 .withDateOfModificationBetween(dateOfModificationFrom, dateOfModificationTo)
           		 .orderBy(orderBy, descDirection).replaceLastAnd().replaceWhereIfQueryIsEmpty().getQuery().toString().trim();
			System.out.println(message);
			//System.out.println(LOGGER.info(message)); 
			
								
        try (Connection connection = JdbcConnector.getConnection();
        		
             PreparedStatement statement = connection.prepareStatement(new JdbcQueryExecutor().fromTable(tableName).withId(id)
            		 .withNicknameOrName(nicknameOrNameLike).withBreed(breed)
            		 .withGender(gender).withPuppiesMoreThan(minPuppiesCount)
            		 .withDateOfBirthBetween(dateOfBirthFrom, dateOfBirthTo)
            		 .withDateOfCreationBetween(dateOfCreationFrom, dateOfCreationTo)
            		 .withDateOfModificationBetween(dateOfModificationFrom, dateOfModificationTo)
            		 .orderBy(orderBy, descDirection).replaceLastAnd().replaceWhereIfQueryIsEmpty().getQuery().toString().trim())) {
        	System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idResult = (long) rs.getInt(1);
                String nicknameResult = rs.getString(2);
                String nameResult = rs.getString(3);
                LocalDate dateOfBirthResult = (LocalDate) rs.getObject(4);
                Dog.Breed breedResult = (Dog.Breed) rs.getObject(5);
                Dog.Gender genderResult = (Dog.Gender) rs.getObject(6);
                Integer puppiesResult = rs.getInt(7);
                LocalDateTime dateOfCreationResult = (LocalDateTime) rs.getObject(8);
                LocalDateTime dateOfModificationResult = (LocalDateTime) rs.getObject(9);
                
                dogs.add(new Dog(idResult, nicknameResult, nameResult
                		, genderResult, breedResult, dateOfBirthResult
                		, puppiesResult, dateOfCreationResult, dateOfModificationResult));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dogs;
		
	}
	
	public StringBuilder getQuery() {
		return query;
	}
	
	public JdbcQueryExecutor fromTable(String tableName) {		
		query.append(tableName + " WHERE ");
		return this;
	}
	
	public JdbcQueryExecutor withId(Integer id) {
		if (id != null)
		query.append("id=" + id + " AND ");
		return this;
		
	}
	
	public JdbcQueryExecutor withNicknameOrName(String nicknameOrNameLike) {
		if (nicknameOrNameLike != null && !nicknameOrNameLike.isEmpty())
		query.append("nickname LIKE %" + nicknameOrNameLike + "% OR name LIKE %" + nicknameOrNameLike + "% AND ");
		return this;
		
	}
	
	public JdbcQueryExecutor withBreed(String breed) {
		if (breed != "all") {
			for (Dog.Breed breedName : Dog.Breed.values()) {
				if (breedName.name().equals(breed)) {			
					query.append("breed=" + breed + " AND ");
					return this;
				}
			}
		}
		return this;
	}
	
	public JdbcQueryExecutor withGender(String gender) {
		if (gender != "all") {
			for (Dog.Gender genderName : Dog.Gender.values()) {
				if (genderName.name().equals(gender)) {			
					query.append("gender=" + gender + " AND ");
					return this;
				}
			}
		}
		return this;
	}
	
	/*
	public JdbcQueryExecutor and() {
		query.append("AND ");
		return this;
	}
	*/
	
	public JdbcQueryExecutor withPuppiesMoreThan(Integer minPuppiesCount) {
		if (minPuppiesCount != null && minPuppiesCount > 0)
			query.append("puppies_count > " + minPuppiesCount + " AND ");
		return this;
		
	}
	
	public JdbcQueryExecutor withDateOfBirthBetween(LocalDate dateOfBirthFrom, LocalDate dateOfBirthTo) {
		if (dateOfBirthFrom != null && dateOfBirthTo != null) {
			query.append("date_of_birth BETWEEN " + dateOfBirthFrom + " AND " + dateOfBirthTo + " ");
		} else if (dateOfBirthFrom != null) {
			query.append("date_of_birth >= " + dateOfBirthFrom + " AND ");
		} else if (dateOfBirthTo != null) {
			query.append("date_of_birth <= " + dateOfBirthTo + " AND ");
		}
		return this;
	}
	
	public JdbcQueryExecutor withDateOfCreationBetween(LocalDateTime dateOfCreationFrom,LocalDateTime dateOfCreationTo) {
		if (dateOfCreationFrom != null && dateOfCreationTo != null) {
			query.append("created_at BETWEEN " + dateOfCreationFrom + " AND " + dateOfCreationTo + " ");
		} else if (dateOfCreationFrom != null) {
			query.append("created_at >= " + dateOfCreationFrom + " AND ");
		} else if (dateOfCreationTo != null) {
			query.append("created_at <= " + dateOfCreationTo + " AND ");
		}
		return this;
		
	}
	
	public JdbcQueryExecutor withDateOfModificationBetween(LocalDateTime dateOfModificationFrom,LocalDateTime dateOfModificationTo) {
		if (dateOfModificationFrom != null && dateOfModificationTo != null) {
			query.append("modified_at BETWEEN " + dateOfModificationFrom + " AND " + dateOfModificationTo + " ");
		} else if (dateOfModificationFrom != null) {
			query.append("modified_at >= " + dateOfModificationFrom + " AND ");
		} else if (dateOfModificationTo != null) {
			query.append("modified_at <= " + dateOfModificationTo + " AND ");
		}
		return this;
		
	}
	
	public JdbcQueryExecutor orderBy(String orderBy, boolean descDirection) {
		if (orderBy != null && !orderBy.isEmpty())
		query.append("ORDER BY " + orderBy + " ");
		if (descDirection) query.append("DESC ");
		return this;
	}
	
	public JdbcQueryExecutor replaceLastAnd() {
		if (query.lastIndexOf("AND ") != -1)
		query.replace(query.lastIndexOf("AND "), query.lastIndexOf("AND ") + 5, "");
		query.append(";");
		return this;
	}
	
	public JdbcQueryExecutor replaceWhereIfQueryIsEmpty() {
		if (query.length() - query.lastIndexOf("WHERE ") <= 7)
			query.replace(query.lastIndexOf("WHERE "), query.lastIndexOf("WHERE ") + 7, "");
		return this;
	}
	
}
