package com.terentyev.transportation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terentyev.transportation.entities.Worker;
import com.terentyev.transportation.services.BossService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boss/drivers")
public class TruckmanController {
	private BossService bossService;
	
	
	@Autowired
	public TruckmanController(BossService bossService) {
		super();
		this.bossService = bossService;
	}

	@GetMapping
	public String drivers(Model model) {
		model.addAttribute("drivers", bossService.findAllDrivers());
		model.addAttribute("driver", new Worker());
		return "drivers";
		
	}
	
	@GetMapping("/add")
	public String addPage(Model model) {
		model.addAttribute("driver", new Worker());
		return "addDriver";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute @Valid Worker driverToAdd, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) bossService.addDriver(driverToAdd);	
		return drivers(model);
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("driver", bossService.findDriverById(id));
		return "editDriver";
	}
	
	@PatchMapping("/edit/{id}")
	public String update(@ModelAttribute @Valid Worker editedDriver, @PathVariable Long id, Model model) {
		bossService.updateDriver(editedDriver, id);
		return drivers(model);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		bossService.deleteDriver(id);		
		return drivers(model);
		
	}
}
