package com.ensat.services;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Reservation;
import com.ensat.repositories.ReservationRepository;

/**********************************/
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service("ReservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	public void setReservationRepository(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public Iterable<Reservation> listAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation getReservationById(Integer id) {
		return reservationRepository.findOne(id);
	}

	@Override
	public Reservation saveReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public void deleteReservation(Integer id) {
		reservationRepository.delete(id);
	}

	@Override
	public Iterable<Reservation> findReservationsByParamImpl() {
		return reservationRepository.findReservationsByParam();
	}

	@Override
	public void sendEmail(Reservation reservation, String typ) {
		String emailFrom = "bsolibia@gmail.com";

		String password = " basile2019";
		String emailTo = reservation.getClient().getEmail();
		String sujet = "Votre reservation NBTrans No " + String.valueOf(reservation.getIdreservation());
		sujet = typ == "C" ? "Votre confirmation NBTrans No " + String.valueOf(reservation.getIdreservation()) : sujet;
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("DÉTAIL DU VOYAGE:");

		String pattern = "dd-MM-yyyy";
		String pattern2 = "HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
				
		String date = simpleDateFormat.format(reservation.getVoyage().getDatDep());
		String heure = simpleDateFormat2.format(reservation.getVoyage().getHeureDep());

		stringBuilder.append("\nDépart: " + reservation.getVoyage().getvDep().getLibelle());
		stringBuilder.append("\nDate: " + date+" à "+ heure);

		stringBuilder.append("\nArrivée: " + reservation.getVoyage().getvAriv().getLibelle());

		stringBuilder.append("\nPrix: " + reservation.getVoyage().getPrix());

		stringBuilder.append("\nRENSEIGNEMENTS SUR LE PASSAGER:");

		stringBuilder.append("\nNom: " + reservation.getClient().getNom());
		stringBuilder.append("\nPrénom " + reservation.getClient().getPrenom());
		stringBuilder.append("\nTéléphone: " + reservation.getClient().getTel());

		String message = stringBuilder.toString();

		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.timeout", "10000");
		props.put("mail.smtp.ssl.checkserveridentity", "false");
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.connectiontimeout", "10000");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailFrom, password);
			}
		});

		try {
			Message mailMessage = new MimeMessage(session);
			mailMessage.setFrom(new InternetAddress(emailFrom));
			mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));

			mailMessage.setSubject(sujet);
			mailMessage.setText(message);
			Transport.send(mailMessage);
			System.out.println("Email envoye avec succes");
		} catch (MessagingException e) {
			System.out.println("Unable to send mail" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
