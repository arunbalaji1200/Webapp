package com.cts.gsd.model;

import java.sql.Date;
import java.sql.Time;

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
@Table(name="RepairLaptop")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id",scope = RepairLaptop.class)
public class RepairLaptop {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="laptop_model")
	private String model;

	@Column(name="warranty_date")
	private Date warranty;

	@Column(name="repair_location")
	private String repairloc;
	
	@Column(name="time_slot")
	private Time slot;
	
	@OneToOne(mappedBy = "repairlaptop")
	@JsonIgnoreProperties(value = {"newlaptop","repairlaptop"})
	private RequestForm requestform;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getWarranty() {
		return warranty;
	}

	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}

	public String getRepairloc() {
		return repairloc;
	}

	public void setRepairloc(String repairloc) {
		this.repairloc = repairloc;
	}

	public Time getSlot() {
		return slot;
	}

	public void setSlot(Time slot) {
		this.slot = slot;
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
