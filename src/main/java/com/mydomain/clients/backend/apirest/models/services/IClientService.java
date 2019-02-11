package com.mydomain.clients.backend.apirest.models.services;

import java.util.List;

import com.mydomain.clients.backend.apirest.models.entity.Client;

public interface IClientService {
	
	public List<Client> findAll();

}
