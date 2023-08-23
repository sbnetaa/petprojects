package com.example.demo6.jdbctemplateaddons;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo6.entities.Dog;

@Service
public class JdbcTemplateExecutor {
	private JdbcTemplate jdbcTemplate;
	private StringBuilder query = new StringBuilder("SELECT * FROM ");
	
	 
	@Autowired
	public JdbcTemplateExecutor(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		
	}

	public JdbcTemplateExecutor(){}
	
	public static List<Dog> find(String tableName, Integer id, String nicknameOrNameLike
			, String gender, String breed
			, LocalDate dateOfBirthFrom, LocalDate dateOfBirthTo
			, Integer minPuppiesCount
			, LocalDateTime dateOfCreationFrom, LocalDateTime dateOfCreationTo
			, LocalDateTime dateOfModificationFrom, LocalDateTime dateOfModificationTo
			, String orderBy, boolean descDirection) {
		
		
				String message = new JdbcTemplateExecutor().fromTable(tableName).withId(id)
           		 .withNicknameOrName(nicknameOrNameLike).withBreed(breed)
           		 .withGender(gender).withPuppiesMoreThan(minPuppiesCount)
           		 .withDateOfBirthBetween(dateOfBirthFrom, dateOfBirthTo)
           		 .withDateOfCreationBetween(dateOfCreationFrom, dateOfCreationTo)
           		 .withDateOfModificationBetween(dateOfModificationFrom, dateOfModificationTo)
           		 .orderBy(orderBy, descDirection).replaceLastAnd().replaceWhereIfQueryIsEmpty().getQuery().toString().trim();
			System.out.println(message);
			
			
								
       JdbcTemplateExecutor jdbcte = new JdbcTemplateExecutor().fromTable(tableName).withId(id)
            		 .withNicknameOrName(nicknameOrNameLike).withBreed(breed)
            		 .withGender(gender).withPuppiesMoreThan(minPuppiesCount)
            		 .withDateOfBirthBetween(dateOfBirthFrom, dateOfBirthTo)
            		 .withDateOfCreationBetween(dateOfCreationFrom, dateOfCreationTo)
            		 .withDateOfModificationBetween(dateOfModificationFrom, dateOfModificationTo)
            		 .orderBy(orderBy, descDirection).replaceLastAnd().replaceWhereIfQueryIsEmpty();//.getQuery().toString().trim())
       				return jdbcte.jdbcTemplate.query(jdbcte.getQuery().toString(), new DogMapper());
            
	}
	
	public StringBuilder getQuery() {
		return query;
	}
	
	public void setQuery(StringBuilder query) {
		this.query = query;
	}
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplateExecutor fromTable(String tableName) {		
		query.append(tableName + " WHERE ");
		return this;
	}
	
	public JdbcTemplateExecutor withId(Integer id) {
		if (id != null)
		query.append("id=" + id + " AND ");
		return this;
		
	}
	
	public JdbcTemplateExecutor withNicknameOrName(String nicknameOrNameLike) {
		if (nicknameOrNameLike != null && !nicknameOrNameLike.isEmpty())
		query.append("nickname LIKE %" + nicknameOrNameLike + "% OR name LIKE %" + nicknameOrNameLike + "% AND ");
		return this;
		
	}
	
	public JdbcTemplateExecutor withBreed(String breed) {
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
	
	public JdbcTemplateExecutor withGender(String gender) {
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
	
	
	public JdbcTemplateExecutor withPuppiesMoreThan(Integer minPuppiesCount) {
		if (minPuppiesCount != null && minPuppiesCount > 0)
			query.append("puppies_count > " + minPuppiesCount + " AND ");
		return this;
		
	}
	
	public JdbcTemplateExecutor withDateOfBirthBetween(LocalDate dateOfBirthFrom, LocalDate dateOfBirthTo) {
		if (dateOfBirthFrom != null && dateOfBirthTo != null) {
			query.append("date_of_birth BETWEEN " + dateOfBirthFrom + " AND " + dateOfBirthTo + " ");
		} else if (dateOfBirthFrom != null) {
			query.append("date_of_birth >= " + dateOfBirthFrom + " AND ");
		} else if (dateOfBirthTo != null) {
			query.append("date_of_birth <= " + dateOfBirthTo + " AND ");
		}
		return this;
	}
	
	public JdbcTemplateExecutor withDateOfCreationBetween(LocalDateTime dateOfCreationFrom,LocalDateTime dateOfCreationTo) {
		if (dateOfCreationFrom != null && dateOfCreationTo != null) {
			query.append("created_at BETWEEN " + dateOfCreationFrom + " AND " + dateOfCreationTo + " ");
		} else if (dateOfCreationFrom != null) {
			query.append("created_at >= " + dateOfCreationFrom + " AND ");
		} else if (dateOfCreationTo != null) {
			query.append("created_at <= " + dateOfCreationTo + " AND ");
		}
		return this;
		
	}
	
	public JdbcTemplateExecutor withDateOfModificationBetween(LocalDateTime dateOfModificationFrom,LocalDateTime dateOfModificationTo) {
		if (dateOfModificationFrom != null && dateOfModificationTo != null) {
			query.append("modified_at BETWEEN " + dateOfModificationFrom + " AND " + dateOfModificationTo + " ");
		} else if (dateOfModificationFrom != null) {
			query.append("modified_at >= " + dateOfModificationFrom + " AND ");
		} else if (dateOfModificationTo != null) {
			query.append("modified_at <= " + dateOfModificationTo + " AND ");
		}
		return this;
		
	}
	
	public JdbcTemplateExecutor orderBy(String orderBy, boolean descDirection) {
		if (orderBy != null && !orderBy.isEmpty())
		query.append("ORDER BY " + orderBy + " ");
		if (descDirection) query.append("DESC ");
		return this;
	}
	
	public JdbcTemplateExecutor replaceLastAnd() {
		if (query.lastIndexOf("AND ") != -1)
		query.replace(query.lastIndexOf("AND "), query.lastIndexOf("AND ") + 5, "");
		query.append(";");
		return this;
	}
	
	public JdbcTemplateExecutor replaceWhereIfQueryIsEmpty() {
		if (query.length() - query.lastIndexOf("WHERE ") <= 7)
			query.replace(query.lastIndexOf("WHERE "), query.lastIndexOf("WHERE ") + 7, "");
		return this;
	}
	
}
