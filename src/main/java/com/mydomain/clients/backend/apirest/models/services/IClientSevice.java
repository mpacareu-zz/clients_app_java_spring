package com.mydomain.clients.backend.apirest.models.services;

import java.util.List;

import com.mydomain.clients.backend.apirest.models.entity.Client;


public interface IClientSevice {
	
	public List<Client> findAll();
	
	public Client findById(Long id);
	
	public Client save (Client client);
	
	public void delete(Long id);

}
