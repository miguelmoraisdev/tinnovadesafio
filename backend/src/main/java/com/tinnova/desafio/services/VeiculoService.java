package com.tinnova.desafio.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinnova.desafio.dto.VeiculoDTO;
import com.tinnova.desafio.dto.VeiculoInsertDTO;
import com.tinnova.desafio.entities.Veiculo;
import com.tinnova.desafio.repositories.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	
	@Transactional
	public VeiculoDTO insert(VeiculoInsertDTO dto) {
		Veiculo entity = new Veiculo();
		entity.setVeiculo(dto.getVeiculo());
		entity.setMarca(dto.getMarca());
		entity.setAno(dto.getAno());
		entity.setDescricao(dto.getDescricao());
		entity.setVendido(dto.isVendido());
		entity.setCreated(Instant.now());
		entity.setUpdated(null);
		entity = repository.save(entity);
		return new VeiculoDTO(entity);
	}

}
