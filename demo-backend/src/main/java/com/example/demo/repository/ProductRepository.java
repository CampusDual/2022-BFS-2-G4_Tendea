package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Shop;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	
	
	
	/**
	 * Busqueda de productos por el nombre, si inicia finaliza e ignorando mayusculas y minisculas (Spring)
	 * @param string para la busqueda
	 * @return List<ProductDTO>
	 */
	public List<Product> findByNameContainingIgnoreCase(String query);
	
	public List<Product> findByShopId(Integer id);
	
	
	/**
	 * Obtiene los productos por categorias
	 * @param categoryId
	 * @return
	 */
	public List<Product> findByCategory(Category category);
	
	
//	@Query("SELECT distinct u FROM User u JOIN u.profiles p WHERE p.id= :id")
//	@Query("SELECT distinct p FROM Product p JOIN Shop s WHERE s.id = :id")
	@Query("SELECT distinct p FROM Product p JOIN p.shop s  WHERE s.id = :id")
	public Page<Product> findByShopPag(@Param("id") Integer id, Pageable pageable);
	
	
	
}
