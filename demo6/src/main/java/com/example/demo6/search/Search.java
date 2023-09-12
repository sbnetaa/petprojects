package com.example.demo6.search;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.example.demo6.entities.Dog;


public class Search {
		private StringBuilder tableName;
		private Integer id;
		private StringBuilder nicknameOrName;
		private StringBuilder breed;
		private StringBuilder gender;
		private Integer minPuppiesCount;
		private LocalDate dateOfBirthFrom;
		private LocalDate dateOfBirthTo;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy hh:mm:ss")
		private LocalDateTime dateOfCreationFrom;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy hh:mm:ss")
		private LocalDateTime dateOfCreationTo;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy hh:mm:ss")
		private LocalDateTime dateOfModificationFrom;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy hh:mm:ss")
		private LocalDateTime dateOfModificationTo;
		private StringBuilder orderBy;
		private boolean descDirection;
		
		@PersistenceContext
		private EntityManager entityManager;
		
		
		public Search(){}
		
		public Search(StringBuilder tableName, Integer id, StringBuilder nicknameOrName, StringBuilder breed, StringBuilder gender,
				Integer minPuppiesCount, LocalDate dateOfBirthFrom, LocalDate dateOfBirthTo,
				LocalDateTime dateOfCreationFrom, LocalDateTime dateOfCreationTo, LocalDateTime dateOfModificationFrom,
				LocalDateTime dateOfModificationTo, StringBuilder orderBy, boolean descDirection) {
			super();
			this.tableName = tableName;
			this.id = id;
			this.nicknameOrName = nicknameOrName;
			this.breed = breed;
			this.gender = gender;
			this.minPuppiesCount = minPuppiesCount;
			this.dateOfBirthFrom = dateOfBirthFrom;
			this.dateOfBirthTo = dateOfBirthTo;
			this.dateOfCreationFrom = dateOfCreationFrom;
			this.dateOfCreationTo = dateOfCreationTo;
			this.dateOfModificationFrom = dateOfModificationFrom;
			this.dateOfModificationTo = dateOfModificationTo;
			this.orderBy = orderBy;
			this.descDirection = descDirection;
			
		}

		
		public List<Dog> getResults() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
			EntityManager em = emf.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Dog> cq = cb.createQuery(Dog.class);
			Root<Dog> root = cq.from(Dog.class);
			List<Predicate> predicates = new ArrayList<>();
			
			if (id != null) predicates.add(cb.like(root.get("id"), "%" + id + "%"));
			if (nicknameOrName != null && !nicknameOrName.isEmpty()) {
				predicates.add(cb.or(
						cb.like(cb.lower(root.get("nickname")), "%" + nicknameOrName.toString().toLowerCase() + "%")
						, cb.like(cb.lower(root.get("name")), "%" + nicknameOrName.toString().toLowerCase() + "%")
						));
			}
			
			if (breed != null && !breed.isEmpty() && !breed.toString().equals("all")) predicates.add(cb.equal(root.get("breed"), Dog.Breed.valueOf(breed.toString())));
			if (gender != null && !gender.isEmpty() && !gender.toString().equals("all")) predicates.add(cb.equal(root.get("gender"), Dog.Gender.valueOf(gender.toString())));
			if (minPuppiesCount != null && minPuppiesCount > 0) predicates.add(cb.greaterThanOrEqualTo(root.get("puppies"), minPuppiesCount));
			if (dateOfBirthFrom != null) predicates.add(cb.greaterThanOrEqualTo(root.get("dateOfBirth"), dateOfBirthFrom));
			if (dateOfBirthTo != null) predicates.add(cb.lessThanOrEqualTo(root.get("dateOfBirth"), dateOfBirthTo));
			if (dateOfCreationFrom != null) predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), dateOfCreationFrom));
			if (dateOfCreationTo != null) predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), dateOfCreationTo));
			if (dateOfModificationFrom != null) predicates.add(cb.greaterThanOrEqualTo(root.get("modifiedAt"), dateOfModificationFrom));
			if (dateOfModificationTo != null) predicates.add(cb.lessThanOrEqualTo(root.get("modifiedAt"), dateOfModificationTo));
			if (orderBy != null) {
				if (descDirection) {
					cq.orderBy(cb.desc(root.get(orderBy.toString())));
				} else {
					cq.orderBy(cb.asc(root.get(orderBy.toString())));
				}	
			}
			
			if (!predicates.isEmpty()) {
				cq.select(root).where(predicates.toArray(new Predicate[0]));
			} else {
				cq.select(root);
			}
			
			TypedQuery<Dog> typedQuery = em.createQuery(cq);
			return typedQuery.getResultList();
			
		}
		
		public StringBuilder getTableName() {
			return tableName;
		}

		public void setTableName(StringBuilder tableName) {
			this.tableName = tableName;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public StringBuilder getNicknameOrName() {
			return nicknameOrName;
		}

		public void setNicknameOrName(StringBuilder nicknameOrName) {
			this.nicknameOrName = nicknameOrName;
		}

		public StringBuilder getBreed() {
			return breed;
		}

		public void setBreed(StringBuilder breed) {
			this.breed = breed;
		}

		public StringBuilder getGender() {
			return gender;
		}

		public void setGender(StringBuilder gender) {
			this.gender = gender;
		}

		public Integer getMinPuppiesCount() {
			return minPuppiesCount;
		}

		public void setMinPuppiesCount(Integer minPuppiesCount) {
			this.minPuppiesCount = minPuppiesCount;
		}

		public LocalDate getDateOfBirthFrom() {
			return dateOfBirthFrom;
		}

		public void setDateOfBirthFrom(LocalDate dateOfBirthFrom) {
			this.dateOfBirthFrom = dateOfBirthFrom;
		}

		public LocalDate getDateOfBirthTo() {
			return dateOfBirthTo;
		}

		public void setDateOfBirthTo(LocalDate dateOfBirthTo) {
			this.dateOfBirthTo = dateOfBirthTo;
		}

		public LocalDateTime getDateOfCreationFrom() {
			return dateOfCreationFrom;
		}

		public void setDateOfCreationFrom(LocalDateTime dateOfCreationFrom) {
			this.dateOfCreationFrom = dateOfCreationFrom;
		}

		public LocalDateTime getDateOfCreationTo() {
			return dateOfCreationTo;
		}

		public void setDateOfCreationTo(LocalDateTime dateOfCreationTo) {
			this.dateOfCreationTo = dateOfCreationTo;
		}

		public LocalDateTime getDateOfModificationFrom() {
			return dateOfModificationFrom;
		}

		public void setDateOfModificationFrom(LocalDateTime dateOfModificationFrom) {
			this.dateOfModificationFrom = dateOfModificationFrom;
		}

		public LocalDateTime getDateOfModificationTo() {
			return dateOfModificationTo;
		}

		public void setDateOfModificationTo(LocalDateTime dateOfModificationTo) {
			this.dateOfModificationTo = dateOfModificationTo;
		}

		public StringBuilder getOrderBy() {
			return orderBy;
		}

		public void setOrderBy(StringBuilder orderBy) {
			this.orderBy = orderBy;
		}

		public boolean isDescDirection() {
			return descDirection;
		}

		public void setDescDirection(boolean descDirection) {
			this.descDirection = descDirection;
		}


		@Override
		public String toString() {
			return "Search [tableName=" + tableName + ", id=" + id + ", nicknameOrName=" + nicknameOrName + ", breed="
					+ breed + ", gender=" + gender + ", minPuppiesCount=" + minPuppiesCount + ", dateOfBirthFrom="
					+ dateOfBirthFrom + ", dateOfBirthTo=" + dateOfBirthTo + ", dateOfCreationFrom="
					+ dateOfCreationFrom + ", dateOfCreationTo=" + dateOfCreationTo + ", dateOfModificationFrom="
					+ dateOfModificationFrom + ", dateOfModificationTo=" + dateOfModificationTo + ", orderBy=" + orderBy
					+ ", descDirection=" + descDirection
					+ "]";
		}
	}
 /*
  * Не работает:
		public String getQuery() {
			String preparedQuery = String.valueOf(this.fromTable(tableName).withId(id)
           		 .withNicknameOrName(nicknameOrName).withBreed(breed)
           		 .withGender(gender).withPuppiesMoreThan(minPuppiesCount)
           		 .withDateOfBirthBetween(dateOfBirthFrom, dateOfBirthTo)
           		 .withDateOfCreationBetween(dateOfCreationFrom, dateOfCreationTo)
           		 .withDateOfModificationBetween(dateOfModificationFrom, dateOfModificationTo)
           		 .orderBy(orderBy, descDirection).replaceLastAnd().replaceWhereIfQueryIsEmpty().query.toString());
			System.out.println(preparedQuery);
			return preparedQuery;
		}
				
		
		public Search fromTable(StringBuilder tableName) {
			//if (query.indexOf(tableName.toString()) != -1)
			query.append(tableName + " WHERE ");
			return this;
		}
		
		public Search withId(Integer id) {
			if (id != null) {
			query.append("id=:id AND ");
			namedParameters.addValue("id", id);
			}
			return this;
			
		}
		
		public Search withNicknameOrName(StringBuilder nicknameOrNameLike) {
			if (nicknameOrNameLike != null && !nicknameOrNameLike.isEmpty()) {
			query.append("nickname LIKE :nickname OR name LIKE :name AND ");
			namedParameters.addValue("nickname", "%" + nicknameOrNameLike + "%");
			namedParameters.addValue("name", "%" + nicknameOrNameLike + "%");
			}
			return this;
			
		}
		
		public Search withBreed(StringBuilder breed) {
			if (breed != null && !breed.toString().equals("all")) {						
				query.append("breed=:breed AND ");
				namedParameters.addValue("breed", Dog.Breed.valueOf(breed.toString()));				
			}
			return this;
		}
		
		public Search withGender(StringBuilder gender) {
			if (gender != null && !gender.toString().equals("all")) {			
				query.append("gender=:gender AND ");
				namedParameters.addValue("gender", Dog.Gender.valueOf(gender.toString()));		
			}
			return this;
		}
		
		
		public Search withPuppiesMoreThan(Integer minPuppiesCount) {
			if (minPuppiesCount != null && minPuppiesCount > 0) {
				query.append("puppies_count > :minPuppiesCount AND ");
				namedParameters.addValue("minPuppiesCount", minPuppiesCount);
			}
			return this;
			
		}
		
		public Search withDateOfBirthBetween(LocalDate dateOfBirthFrom, LocalDate dateOfBirthTo) {
			if (dateOfBirthFrom != null) {
				query.append("date_of_birth >= :dateOfBirthFrom AND ");
				namedParameters.addValue("dateOfBirthFrom", dateOfBirthFrom);
			}
			if (dateOfBirthTo != null) {
				query.append("date_of_birth <= :dateOfBirthTo AND ");
				namedParameters.addValue("dateOfBirthTo", dateOfBirthTo);
			}
			return this;
		}
		
		public Search withDateOfCreationBetween(OffsetDateTime dateOfCreationFrom, OffsetDateTime dateOfCreationTo) {
			if (dateOfCreationFrom != null) {
				query.append("created_at >= :dateOfCreationFrom AND ");
				namedParameters.addValue("dateOfCreationFrom", dateOfCreationFrom);
			}
			if (dateOfCreationTo != null) {
				query.append("created_at <= :dateOfCreationTo AND ");
				namedParameters.addValue("dateOfCreationTo", dateOfCreationTo);
			}
			return this;
			
		}
		
		public Search withDateOfModificationBetween(OffsetDateTime dateOfModificationFrom, OffsetDateTime dateOfModificationTo) {
			if (dateOfModificationFrom != null) {
				query.append("modified_at >= :dateOfModificationFrom AND ");
				namedParameters.addValue("dateOfModificationFrom", dateOfModificationFrom);
			}
			if (dateOfModificationTo != null) {
				query.append("modified_at <= :dateOfModificationTo AND ");
				namedParameters.addValue("dateOfModificationTo", dateOfModificationTo);
			}
			return this;
			
		}
		
		public Search orderBy(StringBuilder orderBy, boolean descDirection) {
			if (orderBy != null && !orderBy.isEmpty()) {
			query.append("ORDER BY :orderBy ");
			namedParameters.addValue("orderBy", orderBy);
			}
			if (descDirection) query.append("DESC"); 
			return this;
		}
		
		public Search replaceLastAnd() {
			if (query.lastIndexOf("AND ") != -1)
			query.replace(query.lastIndexOf("AND "), query.lastIndexOf("AND ") + 5, "");
			return this;
		}
		
		public Search replaceWhereIfQueryIsEmpty() {
			System.out.println(query.length() + " " + query.indexOf("WHERE "));
			if (query.length() - query.indexOf("WHERE ") <= 7)
				query.replace(query.indexOf("WHERE "), query.indexOf("WHERE ") + 7, "");
			return this;
		}


		*/

