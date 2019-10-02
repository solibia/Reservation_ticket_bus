package com.ensat.controllers;

import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensat.entities.Client;
import com.ensat.entities.Reservation;
import com.ensat.entities.User;
import com.ensat.entities.Voyage;
import com.ensat.repositories.ReservationRepository;
import com.ensat.repositories.UserRepository;
import com.ensat.repositories.VoyageRepository;
import com.ensat.services.VilleService;
import com.ensat.services.VoyageService;

/**
 * Homepage controller.
 */
@Controller
public class IndexController {

	private UserRepository userRepository;
	private VilleService villeService;
	private VoyageService voyageService;
	private VoyageRepository voyageRepository;
	private ReservationRepository reservationRepository;

	public IndexController(UserRepository userRepository, VilleService villeService, VoyageService voyageService,
			VoyageRepository voyageRepository, ReservationRepository reservationRepository) {
		this.userRepository = userRepository;
		this.reservationRepository = reservationRepository;
		this.villeService = villeService;
		this.voyageService = voyageService;
		this.voyageRepository = voyageRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String index(Model model) {
		model.addAttribute("villes", villeService.listAllVilles());
		Voyage v = new Voyage();
		v.setDatDep(new Date());
		model.addAttribute("voyage", v);
		return "index";
	}

	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String searchVoyage(Model model, Voyage v) {
		Iterable<Voyage> voyages = voyageRepository.findVoyageByParam(v.getvDep().getId(), v.getvAriv().getId(),
				v.getDatDep());
		String rep;
		Iterator<Voyage> voyagesIterator = voyages.iterator();
		if (!voyagesIterator.hasNext()) {
			model.addAttribute("voyage", v);
			rep = "indexerror";
		} else {
			model.addAttribute("voyages", voyages);
			rep = "voyagesligne";
		}
		return rep;
	}

	@RequestMapping("/admin")
	String indexAdmin(Model model) {
		model.addAttribute("user", new User());
		return "/auth";
	}

	@RequestMapping("/auth")
	String loginAdmin(User u, Model model) {
		String rep = "index";
		User newUser = new User();
		newUser = userRepository.findUserByInfo(u.getCa(), u.getPassw());
		if (newUser == null) {
			rep = "/auth";
		} else {
			model.addAttribute("voyages", voyageService.listAllVoyages());
			rep = "voyages";
		}
		return rep;
	}

	@RequestMapping(value = "rsv/{idvoyage}")
	public String reserverVoyage(@PathVariable Integer idvoyage, Model model) {
		String rep;
		Voyage voyage = voyageService.getVoyageById(idvoyage);
		if ((reservationRepository.findNbreReservationByVoyageId(idvoyage)) >= voyage.getNbVoy()) {
			model.addAttribute("voyage", voyage);
			rep = "indexerror2";
		} else {
			Reservation reservation = new Reservation(new Client(), voyage, new Date());
			model.addAttribute("reservation", reservation);
			rep = "reservationform";
		}
		return rep;
	}
}
