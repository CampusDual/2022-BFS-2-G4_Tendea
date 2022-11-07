package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer>, JpaSpecificationExecutor<Shop>  {

}
