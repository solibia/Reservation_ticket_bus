package com.ensat.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String libelle;
    
	@OneToMany(mappedBy="vDep")
    private List<Voyage> voyDep;
    
    @OneToMany(mappedBy="vAriv")
    private List<Voyage> voyAriv;
    
    public List<Voyage> getVoyDep() {
		return voyDep;
	}

	public void setVoyDep(List<Voyage> voyDep) {
		this.voyDep = voyDep;
	}

	public List<Voyage> getVoyAriv() {
		return voyAriv;
	}

	public void setVoyAriv(List<Voyage> voyAriv) {
		this.voyAriv = voyAriv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Ville() {
		id = 0;
	}
	

}
