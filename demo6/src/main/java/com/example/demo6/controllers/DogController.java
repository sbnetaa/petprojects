package com.example.demo6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo6.entities.Dog;
import com.example.demo6.service.DogService;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class DogController extends AbstractController<Dog, DogService> {
	private DogService dogService;
	
	@Autowired
	public DogController(DogService service, DogService dogService) {
		super(service);
		this.dogService = dogService;
	}

	/*
 	@GetMapping
	public String index(Model model) {	
		model.addAttribute("dog", new Dog());
		model.addAttribute("dogs", dogService.find(null));
		return "index";
	}
	*/
	
	@GetMapping(path = {"/search", "", "/"})
	public String index(Model model, @RequestParam(value = "searchRequest", required = false) String searchRequest) {
		//if(searchRequest != null) {
			model.addAttribute("dog", new Dog());
			model.addAttribute("dogs", dogService.find(searchRequest));
		//} else {
		//	model.addAttribute("dogs", dogService.findAll());
		//}
		return "index";
	}
	
	@GetMapping("/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("searchRequest", new StringBuilder());
		model.addAttribute("dog", dogService.findById(id));
		return "editdog";
		
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
