package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.ShopImage;

public interface ShopImageRepository extends JpaRepository<ShopImage, Integer>, JpaSpecificationExecutor<ShopImage>  {
	

}
