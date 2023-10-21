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
import com.terentyev.transportation.entities.Waybill.Company;
import com.terentyev.transportation.services.BossService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/boss/companies")
public class CompanyController {
	private BossService bossService;

	@Autowired
	public CompanyController(BossService bossService) {
		super();
		this.bossService = bossService;
	}
	
	@GetMapping()
	public String companies(Model model) {
		model.addAttribute("companies", bossService.findAllCompanies());
		model.addAttribute("company", new Company());
		return "companies";
	}
	
	@GetMapping("/view/{id}")
	public String viewCompany(@PathVariable Long id, Model model) {
		model.addAttribute("company", bossService.findCompanyById(id));
		return "viewCompany";
	}
	
	@GetMapping("/add")
	public String addPage(Model model) {
		model.addAttribute("company", new Company());
		return "addCompany";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute @Valid Company companyToAdd, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return addPage(model);
		bossService.addCompany(companyToAdd);	
		return companies(model);
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("company", bossService.findCompanyById(id));
		return "editCompany";
	}
	
	@PatchMapping("/edit/{id}")
	public String update(@ModelAttribute @Valid Company editedCompany, @PathVariable Long id, Model model) {
		bossService.updateCompany(editedCompany, id);
		return companies(model);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		bossService.deleteCompany(id);		
		return companies(model);
		
	}

}
