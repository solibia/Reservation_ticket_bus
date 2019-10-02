package com.ensat.services;

import com.ensat.entities.Client;

public interface ClientService {
	   Iterable<Client> listAllClients();
	   
	   Client getClientById(Integer id);
	
	   Client saveClient(Client client);
	   	   
	   void deleteClient(Integer id);
}
