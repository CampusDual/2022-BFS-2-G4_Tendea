package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductDTO;

public interface IProductService {
	
	List<ProductDTO> findAll();
	
	ProductDTO createProduct(ProductDTO createProductRequest);

}
