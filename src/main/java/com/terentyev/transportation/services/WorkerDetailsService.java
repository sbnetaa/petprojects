package com.terentyev.transportation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.terentyev.transportation.entities.Worker;
import com.terentyev.transportation.repositories.WorkerRepository;
import com.terentyev.transportation.security.WorkerDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class WorkerDetailsService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
	@Autowired
	@Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private WorkerRepository workerRepository;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) {    	
        Optional<Worker> worker = workerRepository.findByUsername(username); 
            return new WorkerDetails(worker.orElseThrow(() -> new UsernameNotFoundException("Username not found")));
  
    }
    
    
    public Worker registerNewUserAccount(Worker worker) {
        //worker.setRoles(Collections.singleton(new Role(1L, "ROLE_DRIVER")));
    	worker.setFullName(worker.getLastName() + " " + worker.getFirstName() + " " + worker.getSurName());
        worker.setPassword(bCryptPasswordEncoder.encode(worker.getPassword()));
        return workerRepository.save(worker);
    }
    
    public Worker findById(Long Id) {
    	Optional<Worker> oWorker = workerRepository.findById(Id); 
        return oWorker.orElse(null);    
    }
	
    public Optional<Worker> findByUsername(String name) {
    	Optional<Worker> oWorker = workerRepository.findByUsername(name);
    	return oWorker;
    	
    }
    
}