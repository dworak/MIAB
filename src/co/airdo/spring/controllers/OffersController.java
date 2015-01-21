package co.airdo.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.airdo.spring.dao.Mechanic;
import co.airdo.spring.dao.Offer;
import co.airdo.spring.service.MechanicsService;
import co.airdo.spring.service.OffersService;

@Controller
public class OffersController {
	
	private OffersService offersService;
	private MechanicsService mechanicService;
	
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	
	@Autowired
	public void serMechanicService(MechanicsService mechanicService) {
		this.mechanicService = mechanicService;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		System.out.println("Id is: " + id);
		return "home";
	}
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "error";
	}

	@RequestMapping("/offers")
	public String showOffers(Model model) {
		List<Offer> offers = offersService.getCurrent();
		System.out.println("Oferty" + " " + offers);
		for (Offer o: offers) {
			o.setMechanic(mechanicService.findMechanicIdById(o.getMechanicId()));
		}
		model.addAttribute("offers", offers);
		return "offers";
	}
	
	@RequestMapping("/createoffer")
	public String createOffer(Model model) {
		model.addAttribute("offer", new Offer());
		
		List<Mechanic> mechanics = mechanicService.getCurrent();
		model.addAttribute("mechanics", mechanics);
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result) throws ParseException {

		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "createoffer";
		}
		
// TODO:
//		Find the same format that's consisten with ISO
//		DateFormat format = new SimpleDateFormat("MM dd, yyyy", Locale.ENGLISH);
//		Date date = format.parse(offer.getDueDateString());
		
		offer.setCreateDate(new Date());
		offer.setDueDate(new Date());
		offer.setMechanic(mechanicService.findMechanicIdById(offer.getMechanicId()));
		
		System.out.println(offer);
		if (offer.getId()!=0) {
			offersService.update(offer);
		} else {
			offersService.create(offer);
		}
		return "offercreated";
	}
	
	@RequestMapping(value="/doedit", method=RequestMethod.GET)
	public String doEdit(Model model, Offer offer, @RequestParam("id") int id) {
		offer = offersService.findOfferById(id);
		model.addAttribute("offer",offer);
		List<Mechanic> mechanics = mechanicService.getCurrent();
		model.addAttribute("mechanics", mechanics);
		return "createoffer";
	}
	
	@RequestMapping(value="/dodelete", method=RequestMethod.GET)
	public String doDelete(Model model, @RequestParam("id") int id) {
		offersService.delete(id);
		return "deleted";
	}
}
