package com.ensat.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Voyage entity.
 */
@Entity
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idvoyage;

    @ManyToOne
    @JoinColumn(name="idvdep")
    private Ville vDep;
    
    
    @ManyToOne
    @JoinColumn(name="idvariv")
    private Ville vAriv;

    @DateTimeFormat(pattern="yyyy-MM-dd")    
    private Date datDep;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")    
    private Date datAriv;
    
    @DateTimeFormat(pattern="HH:mm")
    private Date heureDep;
    
    @ManyToOne
    @JoinColumn(name="iduser")
    private User user;
    
    private BigDecimal prix;
    private Integer nbVoy;
    
    @OneToMany(mappedBy="voyage")
    private List<Reservation> reservation;
    
    
	public Voyage() {
		idvoyage = 0;
	}

	public Integer getIdvoyage() {
		return idvoyage;
	}

	public void setIdvoyage(Integer idvoyage) {
		this.idvoyage = idvoyage;
	}

	public Ville getvDep() {
		return vDep;
	}

	public void setvDep(Ville vDep) {
		this.vDep = vDep;
	}

	public Ville getvAriv() {
		return vAriv;
	}

	public void setvAriv(Ville vAriv) {
		this.vAriv = vAriv;
	}

	public Date getDatDep() {
		return datDep;
	}

	public void setDatDep(Date datDep) {
		this.datDep = datDep;
	}

	public Date getDatAriv() {
		return datAriv;
	}

	public void setDatAriv(Date datAriv) {
		this.datAriv = datAriv;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public Integer getNbVoy() {
		return nbVoy;
	}

	public void setNbVoy(Integer nbVoy) {
		this.nbVoy = nbVoy;
	}
	
	public Date getHeureDep() {
		return heureDep;
	}
	
	public void setHeureDep(Date heureDep) {
		this.heureDep = heureDep;
	}
}
