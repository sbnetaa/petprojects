package com.example.demo6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo6.entities.Person;
import com.example.demo6.service.PersonDetailsService;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private PersonDetailsService personDetailsService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new Person());

        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("registrationForm") @Valid Person registringPerson, BindingResult bindingResult, Model model) {
    	System.out.println("Entering saveUser with username " + registringPerson.getUsername());
        if (bindingResult.hasErrors()) {
        	System.out.println("bindingResult.hasErrors()");
            return "registration";
        }
        
        if (!registringPerson.getPassword().equals(registringPerson.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            System.out.println("пароли не совпадают");
            return "registration";
        }
               
        if (personDetailsService.findByUsername(registringPerson.getUsername()).isPresent()) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            System.out.println("oshibka");
            return "registration";
        }
        
        System.out.println("Prefinished registration");
        personDetailsService.registerNewUserAccount(registringPerson);
        System.out.println("registration finished");

        return "redirect:/";
    }
}
