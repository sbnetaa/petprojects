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
import org.springframework.web.bind.annotation.RequestParam;

import com.terentyev.transportation.entities.FuelData;
import com.terentyev.transportation.services.BossService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boss/fuelData")
public class FuelDataController {
	private BossService bossService;

	@Autowired
	public FuelDataController(BossService bossService) {
		super();
		this.bossService = bossService;
	}
	
	@GetMapping
	public String fuelData(Model model) {
		model.addAttribute("allFuelData", bossService.findAllFuelData());
		model.addAttribute("fuelData", new FuelData());
		return "fuelData";		
	}
	
	@PostMapping
	public String setCommonFcr(@RequestParam(required = false) int commonFcr, Model model) {
		bossService.setCommonFcr(commonFcr);
		return fuelData(model);
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute @Valid FuelData fuelDataToAdd, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) bossService.addFuelData(fuelDataToAdd);	
		return fuelData(model);
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("fuelData", bossService.findFuelDataById(id));
		return "editFuelData";
	}
	
	@PatchMapping("/edit/{id}")
	public String update(@ModelAttribute @Valid FuelData editedFuelData, @PathVariable Integer id, Model model) {
		bossService.updateFuelData(editedFuelData, id);
		return fuelData(model);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		bossService.deleteFuelData(id);		
		return fuelData(model);
		
	}
	
}
