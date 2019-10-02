package com.ensat.services;

import com.ensat.entities.Ville;

public interface VilleService {
	
    Iterable<Ville> listAllVilles();

    Ville getVilleById(Integer id);

    Ville saveVille(Ville ville);

    void deleteVille(Integer id);
}
