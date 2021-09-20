package com.tinnova.desafio.dto;

import com.tinnova.desafio.projections.VeiculosPerDecade;

public class VeiculosPerDecadeDTO {
	
	private String decade;
	private Integer qtdVeiculos;
	
	public VeiculosPerDecadeDTO() {
		
	}

	public VeiculosPerDecadeDTO(String decade, Integer qtdVeiculos) {
		this.decade = decade;
		this.qtdVeiculos = qtdVeiculos;
	}
	
	public VeiculosPerDecadeDTO(VeiculosPerDecade projection) {
		this.decade = projection.getDecade();
		this.qtdVeiculos = projection.getQtdVeiculos();
	}

	public String getDecade() {
		return decade;
	}

	public void setDecade(String decade) {
		this.decade = decade;
	}

	public Integer getQtdVeiculos() {
		return qtdVeiculos;
	}

	public void setQtdVeiculos(Integer qtdVeiculos) {
		this.qtdVeiculos = qtdVeiculos;
	}

}
