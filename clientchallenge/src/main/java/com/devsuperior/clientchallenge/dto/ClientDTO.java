package com.devsuperior.clientchallenge.dto;

import java.io.Serializable;
import java.time.Instant;

import com.devsuperior.clientchallenge.entities.Client;

public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String cpf;
	private Double income;
	private Instant birthdate;
	private int children;
	
	
	public ClientDTO() {
		
	}

	public ClientDTO(Client entity){
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.income = entity.getIncome();
		this.birthdate = entity.getBirthdate();
		this.children = entity.getChildren();
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Instant birthdate) {
		this.birthdate = birthdate;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	
	
}
