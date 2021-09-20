package com.tinnova.desafio.dto;

import com.tinnova.desafio.projections.VeiculosPerCompany;

public class VeiculoPerCompanyDTO {
	
	private String marca;
	private Integer qtdVeiculos;
	
	public VeiculoPerCompanyDTO() {
		
	}

	public VeiculoPerCompanyDTO(String marca, Integer qtdVeiculos) {
		this.marca = marca;
		this.qtdVeiculos = qtdVeiculos;
	}
	
	public VeiculoPerCompanyDTO(VeiculosPerCompany projection) {
		this.marca = projection.getMarca();
		this.qtdVeiculos = projection.getQtdVeiculos();
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getQtdVeiculos() {
		return qtdVeiculos;
	}

	public void setQtdVeiculos(Integer qtdVeiculos) {
		this.qtdVeiculos = qtdVeiculos;
	}

}
