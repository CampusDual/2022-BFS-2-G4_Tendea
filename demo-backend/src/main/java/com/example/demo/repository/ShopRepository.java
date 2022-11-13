package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Shop;
import com.example.demo.entity.User;

public interface ShopRepository extends JpaRepository<Shop, Integer>, JpaSpecificationExecutor<Shop>  {
	
	public List<Shop> findByUserId( Integer id);

	
	/**
	 * Busqueda de una tienda por usuario
	 * @param user
	 * @return
	 */
	
	//@Query("SELECT s FROM Shop s WHERE user = ?1")
	public Shop findByUser(User user);
	
	public List<Shop> findAllByUser(User user);
}
