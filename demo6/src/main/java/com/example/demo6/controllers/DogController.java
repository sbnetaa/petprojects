package com.example.demo6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo6.entities.Dog;
import com.example.demo6.service.DogService;

@Controller
@RequestMapping("/")
public class DogController {
	private DogService dogService;
	
	
	@Autowired
	public DogController(DogService dogService) {		
		this.dogService = dogService;
	}

	public DogService getDogService() {
		return dogService;
	}

	public void setDogService(DogService dogService) {
		this.dogService = dogService;
	}

	@GetMapping("")
	public String index(Model model) {	
		//model.addAttribute("isUserAuth", SecurityContextHolder.getContext().getAuthentication().getName() != "anonymousUser");
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("dog", new Dog());
		model.addAttribute("dogs", dogService.findAll());
		System.out.println(dogService.findAll() + "none");
		return "index";
	}
	
	@PostMapping("")
	public String add(@ModelAttribute("dog") Dog dog, Model model) {	
		//model.addAttribute("dog", new Dog());
		dogService.save(dog);
		//if (br.hasErrors()) System.out.println(br.getAllErrors());
		return "redirect:/";
	}
	
}
