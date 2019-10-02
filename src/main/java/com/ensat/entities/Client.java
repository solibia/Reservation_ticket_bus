package com.ensat.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idclient;
    
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    
    @OneToMany(mappedBy="client")
    private List<Reservation> reservation;
    
    
	public Client() {
	}
	
	public Client(String nom, String prenom, String tel, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
	}
	
	public Integer getIdclient() {
		return idclient;
	}
	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    
}
