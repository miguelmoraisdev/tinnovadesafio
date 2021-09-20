package com.tinnova.desafio.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.tinnova.desafio.entities.Veiculo;

@DataJpaTest
public class VeiculoRepositoryTests {
	
	@Autowired
	private VeiculoRepository repository;
	
	private long existingId;
	private long noExinstingId;
	
	
	@BeforeEach
	void setup() throws Exception {
		existingId = 1L;
		noExinstingId = 100L;
	}
	
	@Test
	public void saveSouldPersistWithAutoIncrementWHenIdIsNull() {
		
	}
	
	@Test
	public void deleteShoudDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		
		Optional<Veiculo> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAcessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(noExinstingId);
		});
	}

}
