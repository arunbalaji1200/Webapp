/*
 * 
 */
package com.cts.gsd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity	
@Table(name="NewLaptop")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id",scope = NewLaptop.class)
public class NewLaptop {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="laptop_model")
	private String model;
	
	@Column(name="RAM_size")
	private String ramsize;
	
	@Column(name="laptop_capacity")
	private String capacity;
	
	@Column(name="OS")
	private String os;
	
	@Column(name="location")
	private String location;
	
	@OneToOne(mappedBy = "newlaptop")
	@JsonIgnoreProperties(value = {"newlaptop","repairlaptop"})
	private RequestForm requestform;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRamsize() {
		return ramsize;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setRamsize(String ramsize) {
		this.ramsize = ramsize;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RequestForm getRequestform() {
		return requestform;
	}

	public void setRequestform(RequestForm requestform) {
		this.requestform = requestform;
	}

	
	
}
