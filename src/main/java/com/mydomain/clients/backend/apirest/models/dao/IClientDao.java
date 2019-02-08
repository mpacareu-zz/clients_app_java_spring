package com.mydomain.clients.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mydomain.clients.backend.apirest.models.entity.Client;


public interface IClientDao extends CrudRepository<Client, Long>{

}
