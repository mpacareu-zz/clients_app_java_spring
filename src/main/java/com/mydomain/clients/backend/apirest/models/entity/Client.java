package com.mydomain.clients.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")

//cuando la tabal de la BD se llama igual a la clase no es necesario usar la notacion Table para especificar el name

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "must not be empty")
	@Size(min = 4, max = 12, message = "size must be between 4 and 12")
	@Column(nullable = false)
	private String name;
	
	@NotEmpty(message = "must not be empty")
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty(message = "must not be empty")
	@Email(message = "must be a well-formed email address")
	@Column(nullable = false, unique=false)
	private String email;
	
	@NotNull (message = "must not be empty")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
//	@PrePersist
//	public void prePersist() {
//		createAt = new Date();
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
