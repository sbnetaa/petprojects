package com.terentyev.transportation.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.terentyev.transportation.security.WorkerDetails;

@Controller
@RequestMapping("/boss")
public class BossController {


	@GetMapping
	public String boss(@AuthenticationPrincipal WorkerDetails workerDetails) {
		return "boss";
	}	
}
