package com.example.demo6.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo6.entities.Dog;
import com.example.demo6.repositories.DogRepository;

@Service
public class DogService extends AbstractEntityServiceImpl<Dog, DogRepository> {
	private DogRepository dogRepository;
	
	@Autowired
	public DogService(DogRepository repository, DogRepository dogRepository) {
		super(repository);
		this.dogRepository = dogRepository;
	}

	/*public List<Dog> findAll(){
		if (searchRequest != null) return this.find(searchRequest);
		return dogRepository.findAll();
	}
	*/
	
	public List<Dog> find(String searchRequest){
		
		if (searchRequest != null) return dogRepository.findAll().stream()
				.filter(dog -> dog.getName().toLowerCase().contains(searchRequest.toLowerCase()) 
				|| dog.getNickname().toLowerCase().contains(searchRequest.toLowerCase()))
				.collect(Collectors.toList());
		return dogRepository.findAll();
		
		
		/*if (searchRequest != null) return dogRepository.findAll().stream()
				.filter(dog -> StringUtils.containsIgnoreCase(dog.getName(), searchRequest) || dog.getNickname().contains(searchRequest))
				.collect(Collectors.toList());
		return dogRepository.findAll();
		*/
	}

	public Dog save(Dog dog){
		return dogRepository.save(dog);
	}
	
	@Transactional(readOnly = false)
	public Dog update(int id, Dog updatedDog){
		Dog dogToUpdate = dogRepository.findById((long) id).get();
		System.out.println(dogToUpdate.toString());
		dogToUpdate.setNickname(updatedDog.getNickname());
		dogToUpdate.setName(updatedDog.getName());
		dogToUpdate.setGender(updatedDog.getGender());
		dogToUpdate.setBreed(updatedDog.getBreed());
		dogToUpdate.setDateOfBirth(updatedDog.getDateOfBirth());
		dogToUpdate.setPuppies(updatedDog.getPuppies());
		dogToUpdate.setModifiedAt(LocalDateTime.now());
		System.out.println(dogToUpdate.toString());
		System.out.println(updatedDog.toString());
		Dog savedDog = dogRepository.save(dogToUpdate);
		System.out.println("done");
		return savedDog;
	}
	
	@Transactional(readOnly = false)
	public void delete(int id) {
		dogRepository.deleteById((long) id);
	}
	
	public Dog findById(int id){		
		return dogRepository.findById((long) id).get();
		
	}
}
