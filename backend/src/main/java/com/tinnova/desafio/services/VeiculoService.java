package com.tinnova.desafio.services;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinnova.desafio.dto.NumberUnsoldDTO;
import com.tinnova.desafio.dto.UpdatePutVeiculoDTO;
import com.tinnova.desafio.dto.UpdateVeiculoDTO;
import com.tinnova.desafio.dto.VeiculoDTO;
import com.tinnova.desafio.dto.VeiculoInsertDTO;
import com.tinnova.desafio.dto.VeiculoPerCompanyDTO;
import com.tinnova.desafio.dto.VeiculosPerDecadeDTO;
import com.tinnova.desafio.entities.Veiculo;
import com.tinnova.desafio.projections.VeiculosPerCompany;
import com.tinnova.desafio.projections.VeiculosPerDecade;
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
	
	@Transactional(readOnly = true)
	public VeiculoDTO findById(Long id) {
		Optional<Veiculo> obj = repository.findById(id);
		Veiculo entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new VeiculoDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public NumberUnsoldDTO numberOfUnsold() {
		Integer quantity = repository.numberOfUnsoldVehicles();
		String message = "Numero de veículos não vendidos";
		return new NumberUnsoldDTO(message, quantity);
	}
	
	@Transactional(readOnly = true)
	public List<VeiculoPerCompanyDTO> getQtdVeiculosPerCompany() {
		List<VeiculosPerCompany> projection = repository.getVeiculosPerCompany();
		return projection.stream().map(x -> new VeiculoPerCompanyDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	public List<VeiculoDTO> getlastVehiclesRegistred() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -8);
		Date initialDate = cal.getTime();
		List<Veiculo> list = repository.getLastVeiculos(initialDate);
		return list.stream().map(x -> new VeiculoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<VeiculosPerDecadeDTO> getVehiclesPerDecade() {
		List<VeiculosPerDecade> list = repository.getVeiculosPerDecade();
		return list.stream().map(x -> new VeiculosPerDecadeDTO(x)).collect(Collectors.toList());
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
	
	@Transactional
	public VeiculoDTO updatePut(Long id, UpdatePutVeiculoDTO dto) {
		try {
			Veiculo entity = repository.getOne(id);
			entity.setVeiculo(dto.getVeiculo());
			entity.setMarca(dto.getMarca());
			entity.setAno(dto.getAno());
			entity.setDescricao(dto.getDescricao());
			entity.setVendido(dto.isVendido());
			entity.setUpdated(Instant.now());
			entity = repository.save(entity);
			return new VeiculoDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional
	public VeiculoDTO updatePatch(Long id, UpdateVeiculoDTO dto) {
		try {
			Veiculo entity = repository.getOne(id);
			entity.setVendido(dto.isVendido());
			entity.setUpdated(Instant.now());
			entity = repository.save(entity);
			return new VeiculoDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
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
