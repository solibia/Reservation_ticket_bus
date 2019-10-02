package com.ensat.services;


import com.ensat.entities.Payement;

public interface PayementService {
	   Iterable<Payement> listAllPayements();
	   
	   Payement getPayementById(Integer id);
	
	   Payement savePayement(Payement payement);
	   	   
	   void deletePayement(Integer id);
}
