package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Payement;
import com.ensat.repositories.PayementRepository;

@Service("PayementService")
public class PayementServiceImpl implements PayementService {

	
	private PayementRepository payementRepository;

	@Autowired
	public PayementServiceImpl(PayementRepository payementRepository) {
		super();
		this.payementRepository = payementRepository;
	}

	@Override
	public Iterable<Payement> listAllPayements() {
        return payementRepository.findAll();
	}

	@Override
	public Payement getPayementById(Integer id) {
        return payementRepository.findOne(id);
	}

	@Override
	public Payement savePayement(Payement payement) {
        return payementRepository.save(payement);
	}

	@Override
	public void deletePayement(Integer id) {
		payementRepository.delete(id);		
	}
}
