package com.mydomain.clients.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mydomain.clients.backend.apirest.models.entity.Client;
import com.mydomain.clients.backend.apirest.models.services.IClientSevice;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientSevice clientService;

	@GetMapping("/clients")
	public List<Client> index() {
		return clientService.findAll();
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		try {
			client = clientService.findById(id);
		} 
		catch(DataAccessException e) 
		{
			response.put("message", "Error performing the query in the database");
			response.put("error", e.getMessage()+ ": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(client == null) {
			response.put("message", "Client ID: "+id.toString()+" does not exist in the database.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@PostMapping("/clients")
	public ResponseEntity<?> create (@RequestBody Client client) {
		Client newClient= null;
		Map<String, Object> response = new HashMap<>();
		try {
			newClient =clientService.save(client);
		} 
		catch(DataAccessException e) 
		{
			response.put("message", "Error performing the insert in the database");
			response.put("error", e.getMessage()+ ": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "The client was created successfully");
		response.put("client", newClient);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/clients/{id}")
	public ResponseEntity<?> update (@RequestBody Client client, @PathVariable Long id) {
		Client currentClient = null;
		Client savedClient = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			currentClient = clientService.findById(id);
		} 
		catch(DataAccessException e) 
		{
			response.put("message", "Error performing the query in the database");
			response.put("error", e.getMessage()+ ": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(currentClient == null) {
			response.put("message", "Error: could not be edited, client ID: "+id.toString()+" does not exist in the database.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentClient.setName(client.getName());
			currentClient.setLastName(client.getLastName());
			currentClient.setEmail(client.getEmail());
			currentClient.setCreateAt(client.getCreateAt());
			savedClient =clientService.save(currentClient);
		} 
		catch(DataAccessException e) 
		{
			response.put("message", "Error performing the update in the database");
			response.put("error", e.getMessage()+ ": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "The client was updated successfully");
		response.put("client", savedClient);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		
	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clientService.delete(id);
		} 
		catch(DataAccessException e) 
		{
			response.put("message", "Error performing the delete in the database");
			response.put("error", e.getMessage()+ ": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "The client was deleted successfully");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
