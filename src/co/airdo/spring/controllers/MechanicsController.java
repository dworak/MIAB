package co.airdo.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.airdo.spring.dao.Mechanic;
import co.airdo.spring.dao.Offer;
import co.airdo.spring.service.MechanicsService;

@Controller
public class MechanicsController {
	
	private MechanicsService mechanicsService;
	
	@Autowired
	public void setMechanicsService(MechanicsService mechanicsService) {
		this.mechanicsService = mechanicsService;
	}
	
	@RequestMapping("/mechanics")
	public String showOffers(Model model) {
		List<Mechanic> mechanics = mechanicsService.getCurrent();
		model.addAttribute("mechanics", mechanics);
		return "mechanics";
	}
	
	@RequestMapping("/createmechanic")
	public String createOffer(Model model) {
		model.addAttribute("mechanic", new Mechanic());
		return "createmechanic";
	}
	
	@RequestMapping(value="/docreatemechanic", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Mechanic mechanic, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "createmechanic";
		}
		
		System.out.println(mechanic);
		
		int id = mechanic.getId();
		if (mechanic!=null && id!=0) {
			mechanicsService.update(mechanic);
		} else {
			mechanicsService.create(mechanic);
		}
		return "mechaniccreated";
	}
	
	@RequestMapping(value="/doeditMechanic", method=RequestMethod.GET)
	public String doEdit(Model model, Mechanic mechanic, @RequestParam("id") int id) {
		
		mechanic = mechanicsService.findMechanicIdById(id);
		model.addAttribute("mechanic",mechanic);
		return "createoffer";
	}
	
	@RequestMapping(value="/dodeleteMechanic", method=RequestMethod.GET)
	public String doDelete(Model model, @RequestParam("id") int id) {
		mechanicsService.delete(id);
		return "deleted";
	}
}