package com.ensat.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Payement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idpaye;

    @ManyToOne
    @JoinColumn(name="idrsv")
    private Reservation reservation;

    private String nocarte;
    private Integer pin;
    private String nomProp;
    
    
	public Integer getIdpaye() {
		return idpaye;
	}
	public void setIdpaye(Integer idpaye) {
		this.idpaye = idpaye;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public String getNocarte() {
		return nocarte;
	}
	public void setNocarte(String nocarte) {
		this.nocarte = nocarte;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public String getNomProp() {
		return nomProp;
	}
	public void setNomProp(String nomProp) {
		this.nomProp = nomProp;
	}
	
	public Payement(Reservation reservation, String nocarte, Integer pin, String nomProp) {
		this.reservation = reservation;
		this.nocarte = nocarte;
		this.pin = pin;
		this.nomProp = nomProp;
	}

	public Payement() {

	}
    
}
