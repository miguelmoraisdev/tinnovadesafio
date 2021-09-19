package com.tinnova.desafio.dto;

import java.io.Serializable;

public class UpdateVeiculoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean vendido;
	
	public UpdateVeiculoDTO() {
		
	}

	public UpdateVeiculoDTO(boolean vendido) {
		this.vendido = vendido;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}
	
}
