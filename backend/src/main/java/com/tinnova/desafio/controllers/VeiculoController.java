package com.tinnova.desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinnova.desafio.dto.NumberUnsoldDTO;
import com.tinnova.desafio.dto.UpdatePutVeiculoDTO;
import com.tinnova.desafio.dto.UpdateVeiculoDTO;
import com.tinnova.desafio.dto.VeiculoDTO;
import com.tinnova.desafio.dto.VeiculoInsertDTO;
import com.tinnova.desafio.dto.VeiculoPerCompanyDTO;
import com.tinnova.desafio.services.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService service;
	
	
	@GetMapping
	public ResponseEntity<Page<VeiculoDTO>> findAll(Pageable pageable) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("ano"));
		Page<VeiculoDTO> page = service.findAll(pageRequest);		
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping("/numberOfUnsoldVehicles")
	public ResponseEntity<NumberUnsoldDTO> findNumberOfUnsoldVehicles() {
			NumberUnsoldDTO dto = service.numberOfUnsold();
		return ResponseEntity.ok().body(dto);
	}  
	
	@GetMapping("/amountVehiclesPerCompany")
	public ResponseEntity<List<VeiculoPerCompanyDTO>> findQtdVehiclesPerCompany() {
		List<VeiculoPerCompanyDTO> dto = service.getQtdVeiculosPerCompany();
		return ResponseEntity.ok().body(dto);
	}  
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id){
		VeiculoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<VeiculoDTO> insert(@RequestBody VeiculoInsertDTO insertDTO){
		VeiculoDTO dto = service.insert(insertDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VeiculoDTO> updatePut(@PathVariable Long id, @RequestBody UpdatePutVeiculoDTO dto){
		VeiculoDTO resultDTO = service.updatePut(id, dto);
		return ResponseEntity.ok().body(resultDTO);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @RequestBody UpdateVeiculoDTO dto){
		VeiculoDTO resultDTO = service.updatePatch(id, dto);
		return ResponseEntity.ok().body(resultDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	/*@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	} */

}
