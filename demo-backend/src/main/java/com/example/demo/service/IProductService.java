package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ProductDTO;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IProductService {
	
	List<ProductDTO> findAll();
	
	ProductDTO createProduct(ProductDTO createProductRequest);
	
	DataSourceRESTResponse<List<ProductDTO>> getProducts(AnyPageFilter pageFilter);

}
