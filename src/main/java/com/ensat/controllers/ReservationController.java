package com.ensat.controllers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensat.entities.Reservation;
import com.ensat.entities.Ville;
import com.ensat.repositories.ReservationRepository;
import com.ensat.services.ClientService;
import com.ensat.services.ReservationService;

/**
 * Reservation controller.
 */
@Controller
public class ReservationController {

	private ReservationService reservationService;
	private ClientService clientService;
	private ReservationRepository reservationRepository;

	@Autowired
	public ReservationController(ReservationService reservationService, ClientService clientService,
			ReservationRepository reservationRepository) {
		this.reservationService = reservationService;
		this.clientService = clientService;
		this.reservationRepository = reservationRepository;
	}

	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("reservations", reservationRepository.findReservationsByParam());
		model.addAttribute("unreservation", new Ville());
		return "reservations";
	}

	@RequestMapping(value = "/reserv", method = RequestMethod.POST)
	public String reserverReservation(Ville unreservation, Model model) {
		String rep = "";
		int idRsv = Integer.parseInt(unreservation.getLibelle());
		Reservation reservation = reservationService.getReservationById(idRsv);
		if (reservation == null) {
			rep = "reservationerror";
		} else {
			ArrayList<Reservation> lstRsv = new ArrayList<>();
			lstRsv.add(reservation);
			model.addAttribute("reservations", lstRsv);
			model.addAttribute("unreservation", new Ville());
			rep = "reservations";
		}
		return rep;
	}

	@RequestMapping(value = "reservation", method = RequestMethod.POST)
	public String saveReservation(Reservation reservation) {
		reservation.setClient(clientService.saveClient(reservation.getClient()));
		reservation.setDatRsv(new Date());
		reservation = reservationService.saveReservation(reservation);
		reservationService.sendEmail(reservation, "R");
		return "redirect:/reservation/" + reservation.getIdreservation();
	}

	@RequestMapping("reservation/{idreservation}")
	public String showVoyage(@PathVariable Integer idreservation, Model model) {
		model.addAttribute("reservation", reservationService.getReservationById(idreservation));
		return "reservationshow";
	}

}
