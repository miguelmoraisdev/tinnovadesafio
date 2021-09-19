package com.tinnova.desafio.dto;

import java.io.Serializable;
import java.time.Instant;

import com.tinnova.desafio.entities.Veiculo;

public class VeiculoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String veiculo;
	private String marca;
	private Integer ano;
	private String descricao;
	private boolean vendido;
	private Instant created;
	private Instant updated;
	
	public VeiculoDTO() {
		
	}

	public VeiculoDTO(Long id, String veiculo, String marca, Integer ano, String descricao, boolean vendido,
			Instant created, Instant updated) {
		super();
		this.id = id;
		this.veiculo = veiculo;
		this.marca = marca;
		this.ano = ano;
		this.descricao = descricao;
		this.vendido = vendido;
		this.created = created;
		this.updated = updated;
	}
	
	public VeiculoDTO(Veiculo entity) {
		this.id = entity.getId();
		this.veiculo = entity.getVeiculo();
		this.marca = entity.getMarca();
		this.ano = entity.getAno();
		this.descricao = entity.getDescricao();
		this.vendido = entity.isVendido();
		this.created = entity.getCreated();
		this.updated = entity.getUpdated();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getUpdated() {
		return updated;
	}

	public void setUpdated(Instant updated) {
		this.updated = updated;
	}
	
}
