package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Client;
import com.ensat.repositories.ClientRepository;

@Service("ClientService")
public class ClientServiceImpl implements ClientService {
    
	private ClientRepository clientRepository;


    @Autowired
    public void setUserRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }	
	
	@Override
	public Iterable<Client> listAllClients() {
        return clientRepository.findAll();
	}

	@Override
	public Client getClientById(Integer id) {
        return clientRepository.findOne(id);
	}

	@Override
	public Client saveClient(Client client) {
        return clientRepository.save(client);
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.delete(id);
	}

}
