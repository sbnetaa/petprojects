package com.example.demo6.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DogController {
	
@GetMapping("/")
public static String index(Model model) {	
	model.addAttribute("isUserAuth", SecurityContextHolder.getContext().getAuthentication().getName() != "anonymousUser");
	System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
	return "index";
}
}
