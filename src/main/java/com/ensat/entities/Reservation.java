package com.ensat.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idreservation;
    
    @ManyToOne
    @JoinColumn(name="idclient")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name="idvoyage")
    private Voyage voyage;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")    
    private Date datRsv;
    
    @OneToMany(mappedBy="reservation")
    private List<Payement> payement;
    
	public Integer getIdreservation() {
		return idreservation;
	}

	public void setIdreservation(Integer idreservation) {
		this.idreservation = idreservation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	public Date getDatRsv() {
		return datRsv;
	}

	public void setDatRsv(Date datRsv) {
		this.datRsv = datRsv;
	}

	
	public List<Payement> getPayement() {
		return payement;
	}

	public void setPayement(List<Payement> payement) {
		this.payement = payement;
	}

	public Reservation(Client client, Voyage voyage, Date datRsv) {
		this.client = client;
		this.voyage = voyage;
		this.datRsv = datRsv;
	}

	public Reservation() {
	}
    
    
}
