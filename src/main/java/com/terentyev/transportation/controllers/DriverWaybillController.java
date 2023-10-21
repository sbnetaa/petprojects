package com.terentyev.transportation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terentyev.transportation.entities.Worker;
import com.terentyev.transportation.services.DriverService;

@Controller
@RequestMapping("/driver/waybills")
public class DriverWaybillController {
	private DriverService driverService;

	@Autowired
	public DriverWaybillController(DriverService driverService) {
		super();
		this.driverService = driverService;
	}
	
	@GetMapping
	public String waybills(@AuthenticationPrincipal Worker driver, Model model) {
		model.addAttribute("waybills", driverService.findAllWaybills(driver));
		return "waybills";
	}
}
