package com.tinnova.desafio.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinnova.desafio.dto.VeiculoDTO;
import com.tinnova.desafio.dto.VeiculoInsertDTO;
import com.tinnova.desafio.entities.Veiculo;
import com.tinnova.desafio.repositories.VeiculoRepository;
import com.tinnova.desafio.services.exceptions.DatabaseException;
import com.tinnova.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<VeiculoDTO> findAll(Pageable pageable) {
		Page<Veiculo> page = repository.findAll(pageable);
		return page.map(x -> new VeiculoDTO(x));
	}
	
	
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

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation");
		}
	}
}
