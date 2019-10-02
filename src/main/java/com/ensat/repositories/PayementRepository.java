package com.ensat.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ensat.entities.Payement;

public interface PayementRepository extends CrudRepository<Payement, Integer> {
	
}
