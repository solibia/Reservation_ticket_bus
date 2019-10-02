package com.ensat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensat.entities.Payement;
import com.ensat.services.PayementService;
import com.ensat.services.ReservationService;

@Controller
public class PayementController {

	private PayementService payementService;
	private ReservationService reservationService;

	@Autowired
	public void setPayementService(PayementService payementService, ReservationService reservationService) {
		this.payementService = payementService;
		this.reservationService = reservationService;
	}

	@RequestMapping(value = "/payements", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("payements", payementService.listAllPayements());
		System.out.println("Returning rpayements:");
		return "payements";
	}

	@RequestMapping(value = "payement", method = RequestMethod.POST)
	public String savePayement(Model model, Payement payement) {
		payement = payementService.savePayement(payement);
		reservationService.sendEmail(payement.getReservation(), "C");
		model.addAttribute("payement", payement);
		return "redirect:/payementshow/" + payement.getIdpaye();
	}

	@RequestMapping("payement/{idreservation}")
	public String confirmerPayement(@PathVariable Integer idreservation, Model model) {
		Payement newPayement = new Payement();
		newPayement.setReservation(reservationService.getReservationById(idreservation));
		model.addAttribute("payement", newPayement);
		return "payementform";
	}

	@RequestMapping("payementshow/{idpaye}")
	public String showPayement(@PathVariable Integer idpaye, Model model) {
		model.addAttribute("payement", payementService.getPayementById(idpaye));
		System.out.println("Returning payementshow dans showPayement");
		return "payementshow";
	}
}
