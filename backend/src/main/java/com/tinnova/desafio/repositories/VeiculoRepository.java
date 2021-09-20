package com.tinnova.desafio.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tinnova.desafio.entities.Veiculo;
import com.tinnova.desafio.projections.VeiculosPerCompany;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM tb_veiculo WHERE vendido = false")
	public Integer numberOfUnsoldVehicles();
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT marca,"
			+ " (SELECT COUNT(*) FROM tb_veiculo a WHERE a.marca = a2.marca) AS qtdVeiculos FROM tb_veiculo a2")
	public List<VeiculosPerCompany> getVeiculosPerCompany();
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_veiculo a WHERE"
			+ " a.created > :initialDate")
	public List<Veiculo> getLastVeiculos(Date initialDate);

}
