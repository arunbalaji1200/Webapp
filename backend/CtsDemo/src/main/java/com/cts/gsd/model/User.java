package com.cts.gsd.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name="user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id",scope = User.class)
public class User {
	
	@Id
	@Column(name="id",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="username",unique = true,nullable = false)
	private String userName;
	
	@Column(name="Emailid",unique=true,nullable = false)
	private String email;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="createdOn",nullable = false)
	private Date createdOn;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="role_id",referencedColumnName = "id")
	@JsonIgnoreProperties(value = "user")
	private Role role;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
