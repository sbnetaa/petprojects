package com.example.demo6.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo6.entities.Dog;
import com.example.demo6.service.DogService;

import jakarta.validation.Valid;

@Controller
@RequestMapping
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

	@GetMapping
	public String index(Model model) {	
		model.addAttribute("dog", new Dog());
		model.addAttribute("dogs", dogService.findAll());
		return "index";
	}
	
	@PostMapping
	public String add(@ModelAttribute("dog")@Valid Dog dog, Model model) {	
		dogService.save(dog);
		return "redirect:";
	}
	
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	*/
}
