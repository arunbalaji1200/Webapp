package com.cts.gsd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Request_form")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id",scope = RequestForm.class)
public class RequestForm {
	
	public enum Requesttype{
		RepairLaptop,NewLaptop;
	}	
	
	public enum Status{
		Open, Inprogress, closed, cancelled, deleted, NeedApproval;
	}
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="request_type",nullable = false)
	private Requesttype requesttype;
	
	@Column(name ="requesting_for",nullable = false)
	private String requestingfor;

	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status;
	
	@Column(name="comments")
	private String comments;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JsonIgnoreProperties(value={"requestform","ramsize","capacity","os","location"})
	private NewLaptop newlaptop;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JsonIgnoreProperties(value={"requestform","warranty","repairloc","slot"})
	private RepairLaptop repairlaptop;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Requesttype getRequesttype() {
		return requesttype;
	}

	public void setRequesttype(Requesttype requesttype) {
		this.requesttype = requesttype;
	}

	public String getRequestingfor() {
		return requestingfor;
	}

	public void setRequestingfor(String requestingfor) {
		this.requestingfor = requestingfor;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public NewLaptop getNewlaptop() {
		return newlaptop;
	}

	public void setNewlaptop(NewLaptop newlaptop) {
		this.newlaptop = newlaptop;
	}

	public RepairLaptop getRepairlaptop() {
		return repairlaptop;
	}

	public void setRepairlaptop(RepairLaptop repairlaptop) {
		this.repairlaptop = repairlaptop;
	}
	
}
