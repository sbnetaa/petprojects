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

import com.terentyev.transportation.entities.Vehicle;
import com.terentyev.transportation.entities.Vehicle.Type;
import com.terentyev.transportation.services.BossService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boss/vehicles")
public class VehicleController {
	private BossService bossService;
	
	
	@Autowired
	public VehicleController(BossService bossService) {
		super();
		this.bossService = bossService;
	}

	@GetMapping
	public String vehicles(Model model) {
		model.addAttribute("vehicles", bossService.findAllVehicles(Vehicle.isDisplayTrailers()));
		model.addAttribute("nextFuelData", bossService.findNextFuelData());
		model.addAttribute("displayTrailers", Vehicle.isDisplayTrailers());
		return "vehicles";
		
	}
	
	@GetMapping("/{id}")
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("vehicle", bossService.findVehicleById(id));
		return "viewVehicle";
	}
	
	@GetMapping("/add")
	public String addPage(Model model) {
		model.addAttribute("vehicle", new Vehicle());
		return "addVehicle";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute @Valid Vehicle vehicleToAdd, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return "addVehicle";
		bossService.addVehicle(vehicleToAdd);	
		return vehicles(model);
	}
	
	@PostMapping
	public String separate(@RequestParam(required = false) boolean searchForTrailers, Model model) {
		Vehicle.setDisplayTrailers(searchForTrailers);
		return vehicles(model);
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("vehicle", bossService.findVehicleById(id));
		model.addAttribute("trailerTypes", bossService.findAllTrailerTypes());
		return "editVehicle";
	}
	
	@PatchMapping("/edit/{id}")
	public String update(@ModelAttribute @Valid Vehicle editedVehicle, @PathVariable Long id, Model model, BindingResult br) {
		if (br.hasErrors()) return "editVehicle";
		bossService.updateVehicle(editedVehicle, id);
		return vehicles(model);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		bossService.deleteVehicle(id);		
		return vehicles(model);
		
	}
	
	@GetMapping("/trailerTypes")
	public String trailerTypes(Model model) {
		model.addAttribute("trailerTypes", bossService.findAllTrailerTypes());
		return "trailerTypes";
	}
	
	@GetMapping("/trailerTypes/add")
	public String addTrailerType(Model model) {
		model.addAttribute("trailerType", new Type());
		return "addTrailerType";
	}
		
	@PostMapping("/trailerTypes/add")
	public String saveTrailerType(@ModelAttribute Type trailerType, Model model, BindingResult br) {
		if (br.hasErrors()) return "addTrailerType";
		bossService.addTrailerType(trailerType);
		return trailerTypes(model);
	}
	
	@GetMapping("/trailerTypes/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		model.addAttribute("trailerType", new Type());
		return "editTrailerType";
	}
	
	@PatchMapping("/trailerTypes/edit/{id}")
	public String update(@PathVariable long id, Type trailerType, Model model, BindingResult br) {
		if (br.hasErrors()) return "editTrailerType";
		bossService.updateTrailerType(id, trailerType);
		return trailerTypes(model);
	}
	
	@DeleteMapping("/trailerTypes/delete/{id}")
	public String deleteTrailerType(@PathVariable("id") Long id, Model model) {
		bossService.deleteTrailerType(id);		
		return trailerTypes(model);
		
	}
}
