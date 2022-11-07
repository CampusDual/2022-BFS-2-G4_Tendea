package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	
	
	
	/**
	 * Busqueda de productos por el nombre, si inicia finaliza e ignorando mayusculas y minisculas (Spring)
	 * @param string para la busqueda
	 * @return List<ProductDTO>
	 */
	public List<Product> findByNameContainingIgnoreCase(String query);
	
	
}
