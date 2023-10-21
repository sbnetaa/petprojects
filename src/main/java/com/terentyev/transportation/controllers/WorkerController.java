/*package com.terentyev.transportation.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.terentyev.transportation.entities.Worker;

//@RequestMapping(path = {"", "/", "/login"})
public class WorkerController {

	
	//@GetMapping("")
	public String worker(@AuthenticationPrincipal Worker worker) {
		if (worker.getRoles().stream().anyMatch(role -> role.getName().contains("BOSS"))) return "redirect:/boss";		
		if (worker.getRoles().stream().anyMatch(role -> role.getName().contains("DRIVER"))) return "redirect:/driver";	
		return "login";
		
	}
}
*/