package com.ensat.services;


import com.ensat.entities.Voyage;

public interface VoyageService {

    Iterable<Voyage> listAllVoyages();

    Voyage getVoyageById(Integer idvoyage);

    Voyage saveVoyage(Voyage voyage);

    void deleteVoyage(Integer idvoyage);

    Iterable <Voyage> findVoyageByParamImpl(Voyage v);

}
