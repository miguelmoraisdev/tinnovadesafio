package com.tinnova.desafio.dto;

import java.io.Serializable;

public class NumberUnsoldDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Integer quantity;
	
	public NumberUnsoldDTO() {
		
	}

	public NumberUnsoldDTO(String message, Integer quantity) {
		this.message = message;
		this.quantity = quantity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
