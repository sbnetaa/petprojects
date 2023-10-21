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
import com.terentyev.transportation.entities.Waybill;
import com.terentyev.transportation.services.BossService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boss/waybills")
public class WaybillController {
	private BossService bossService;

	@Autowired
	public WaybillController(BossService bossService) {
		super();
		this.bossService = bossService;
	}
	
	@GetMapping()
	public String waybills(Model model) {
		model.addAttribute("waybills", bossService.findAllWaybills());
		return "waybills";
	}
	
	@GetMapping("/view/{id}")
	public String viewWaybill(@PathVariable Long id, Model model) {
		model.addAttribute("waybill", bossService.findWaybillById(id));
		return "viewWaybill";
	}
	
	@GetMapping("/add")
	public String addPage(Model model) {
		model.addAttribute("waybill", new Waybill());
		return "addWaybill";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute @Valid Waybill waybillToAdd, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return addPage(model);
		bossService.addWaybill(waybillToAdd);	
		return waybills(model);
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("waybill", bossService.findWaybillById(id));
		return "editWaybill";
	}
	
	@PatchMapping("/edit/{id}")
	public String update(@ModelAttribute @Valid Waybill editedWaybill, @PathVariable Long id, Model model) {
		bossService.updateWaybill(editedWaybill, id);
		return waybills(model);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		bossService.deleteWaybill(id);		
		return waybills(model);
		
	}

}
