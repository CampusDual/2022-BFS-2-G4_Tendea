package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.entity.Shop;
import com.example.demo.entity.User;

public interface ShopRepository extends JpaRepository<Shop, Integer>, JpaSpecificationExecutor<Shop>  {
	
	public List<Shop> findByUserId( Integer id);

	
	/**
	 * Busqueda de una tienda por usuario
	 * @param user
	 * @return
	 */
	public Shop findByUser(String login);
	
	/**
	 * Listadod de tiendas por usuario
	 * @param user
	 * @return
	 */
	public List<Shop> findAllByUser(User user);
}
