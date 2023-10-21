package com.terentyev.transportation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terentyev.transportation.entities.Worker;
import com.terentyev.transportation.security.WorkerDetails;
import com.terentyev.transportation.services.WorkerDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private WorkerDetailsService workerDetailsService;

    
    @GetMapping(path = {"", "/", "/login", "/logout"})
    public String index(@AuthenticationPrincipal WorkerDetails workerDetails) {  	
    	if (workerDetails != null) {
    		if (workerDetails.getWorker().isBoss() == true) {
    			return "redirect:/boss";
    		}
    		return "redirect:/driver";
    	} else {
    		return "index";
    	}
    }
    
    
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new Worker());
        return "registration";
    }

    
    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("registrationForm") @Valid Worker registringWorker
    		, BindingResult bindingResult, Model model) {
    	
        if (bindingResult.hasErrors()) return "registration";              
        if (!registringWorker.getPassword().equals(registringWorker.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }              
        if (workerDetailsService.findByUsername(registringWorker.getUsername()).isPresent()) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }        
        workerDetailsService.registerNewUserAccount(registringWorker);


        return "redirect:/";
    }
}
