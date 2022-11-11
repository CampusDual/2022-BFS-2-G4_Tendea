package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Shop;
import com.example.demo.entity.User;

public interface ShopRepository extends JpaRepository<Shop, Integer>, JpaSpecificationExecutor<Shop>  {

	
	/**
	 * Busqueda de una tienda por usuario
	 * @param user
	 * @return
	 */
	
	//@Query("SELECT s FROM Shop s WHERE user = ?1")
	public Shop findByUser(User user);
}
