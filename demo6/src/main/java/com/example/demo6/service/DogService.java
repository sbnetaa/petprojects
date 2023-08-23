package com.example.demo6.service;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo6.entities.Dog;
import com.example.demo6.repositories.DogRepository;
import com.example.demo6.search.Search;

@Service
public class DogService extends AbstractEntityServiceImpl<Dog, DogRepository> {
	private DogRepository dogRepository;
	
	@Autowired
	public DogService(DogRepository repository, DogRepository dogRepository) {
		super(repository);
		this.dogRepository = dogRepository;
		
		
	}
	
	public List<Dog> find(Search searchRequest) {
		return searchRequest.getResults();
	}
		
	/*
	 Здесь происходит выборка ВСЕХ собак из БД, и только затем их сортировка, и только затем их отсеивание. Отказался.
	public List<Dog> find(String searchRequest, String searchBreed, String sortBy, boolean sortAsc){
		
		Sort sort = Sort.by(sortBy).ascending();
		if (!sortAsc) sort = Sort.by(sortBy).descending();
			return dogRepository.findAll(sort).stream()
				.filter(dog -> ((searchRequest == null) || (dog.getName().toLowerCase().contains(searchRequest.toLowerCase()) 
						|| dog.getNickname().toLowerCase().contains(searchRequest.toLowerCase())))
						&& (searchBreed == null || searchBreed.equals("all") || dog.getBreed().getType().contains(searchBreed)))
				.collect(Collectors.toList());						
	}
	*/

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
		dogToUpdate.setModifiedAt(OffsetDateTime.now());
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
