package com.example.demo6.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo6.entities.Person;
import com.example.demo6.entities.Role;
import com.example.demo6.repositories.PersonRepository;
import com.example.demo6.repositories.RoleRepository;
import com.example.demo6.security.PersonDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PersonDetailsService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
	@Autowired
	@Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;

    
    @Override
    public UserDetails loadUserByUsername(String username) {    	
    	if (username != null) System.out.println("Username is " + username);
        Optional<Person> person = personRepository.findByUsername(username); 
        System.out.println("Person is " + person);
            return new PersonDetails(person.orElseThrow(() -> new UsernameNotFoundException("Username not found")));
  
    }
    
    
    public Person registerNewUserAccount(Person person) {
    	System.out.println("Entering registerNewUserAccount");
        person.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }
    
    public Person findById(Long Id) {
    	Optional<Person> oPerson = personRepository.findById(Id); 
        return oPerson.orElse(null);    
    }
	
    public Optional<Person> findByUsername(String name) {
    	System.out.println("Entering findByUsername " + name);
    	Optional<Person> oPerson = personRepository.findByUsername(name);
    	System.out.println("findByUsername Optional is " + oPerson);
    	return oPerson;
    	
    }
    
}