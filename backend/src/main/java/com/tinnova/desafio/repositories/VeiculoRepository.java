package com.tinnova.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tinnova.desafio.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM tb_veiculo WHERE vendido = false")
	public Integer numberOfUnsoldVehicles();

}
