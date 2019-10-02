package com.ensat.services;

import com.ensat.entities.Voyage;
import com.ensat.repositories.VoyageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Voyage service implement.
 */
@Service("VoyageService")
public class VoyageServiceImpl implements VoyageService {

	@Autowired
	private VoyageRepository voyageRepository;

    
    @Autowired
    public void setVoyageRepository(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }

    @Override
    public Iterable<Voyage> listAllVoyages() {
        return voyageRepository.findAll();
    }

    @Override
    public Voyage getVoyageById(Integer idVoyage) {
        return voyageRepository.findOne(idVoyage);
    }

    @Override
    public Voyage saveVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    @Override
    public void deleteVoyage(Integer idVoyage) {
        voyageRepository.delete(idVoyage);
    }


    @Override
    public Iterable <Voyage> findVoyageByParamImpl(Voyage v) {
        return voyageRepository.findVoyageByParam(v.getvDep().getId(), v.getvAriv().getId(), v.getDatDep());
	}    
}
