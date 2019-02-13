package com.mydomain.clients.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mydomain.clients.backend.apirest.models.entity.Client;


public interface IClientSevice {
	
	public List<Client> findAll();
	
	public Page<Client> findAll( Pageable pageable);
	
	public Client findById(Long id);
	
	public Client save (Client client);
	
	public void delete(Long id);

}
