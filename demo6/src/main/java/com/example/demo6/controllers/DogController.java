package com.example.demo6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo6.entities.Dog;
import com.example.demo6.entities.Person;
import com.example.demo6.search.Search;
import com.example.demo6.security.PersonDetails;
import com.example.demo6.service.DogService;
import com.example.demo6.service.PersonDetailsService;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class DogController extends AbstractController<Dog, DogService> {
	private DogService dogService;
	private PersonDetailsService personDetailsService;
	
	@Autowired
	public DogController(DogService service, DogService dogService, PersonDetailsService personDetailsService) {
		super(service);
		this.dogService = dogService;
		this.personDetailsService = personDetailsService;
	}
	
	@GetMapping(path = {"/search", "", "/"})
	public String index(Model model, @ModelAttribute("searchRequest") Search searchRequest) {
			//model.addAttribute("searchRequest", new Search());
			model.addAttribute("dog", new Dog());
			model.addAttribute("dogs", dogService.find(searchRequest));
			return "index";
	}
	
	@GetMapping("/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("dog", dogService.findById(id));
		return "editdog";
		
	}
	
	/*
	@GetMapping("/login")
	public Person login(@AuthenticationPrincipal PersonDetails loginingPerson) {
		if (loginingPerson != null) {
		System.out.println(loginingPerson.getPassword());
		System.out.println(personDetailsService.findByUsername(loginingPerson.getPerson().getName()).getPassword());
		}
		return loginingPerson.getPerson();
		
	}
	*/
	
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute("searchRequest") Search searchRequest) {
		return "redirect:";
	}
	
	@PostMapping
	public String save(@ModelAttribute("dog")@Valid Dog dog, Model model) {	
		dogService.save(dog);
		return "redirect:";
	}
	
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("dog")@Valid Dog updatedDog, @PathVariable("id") int id, Model model) {	
		dogService.update(id, updatedDog);
		return "redirect:";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		dogService.delete(id);
		return "redirect:";
	}
}
