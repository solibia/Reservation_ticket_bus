package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Ville;
import com.ensat.repositories.VilleRepository;

@Service
public class VilleServiceImpl implements VilleService {

    private VilleRepository villeRepository;


    @Autowired
    public void setVilleRepository(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

	@Override
	public Iterable<Ville> listAllVilles() {
        return villeRepository.findAll();
	}

	@Override
	public Ville getVilleById(Integer id) {
        return villeRepository.findOne(id);
	}

	@Override
	public Ville saveVille(Ville ville) {
        return villeRepository.save(ville);
	}

	@Override
	public void deleteVille(Integer id) {
        villeRepository.delete(id);		
	}

}
