package com.ensat.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ensat.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {

}
