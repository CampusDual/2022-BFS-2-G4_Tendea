package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer>, JpaSpecificationExecutor<Shop>  {
	
	public List<Shop> findByUserId( Integer id);

}
