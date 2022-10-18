package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.mapper.ProductMapper;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl extends AbstractDemoService implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<ProductDTO> findAll() {
		return ProductMapper.INSTANCE.productToProductDTOList(productRepository.findAll());
	}
	
	@Override
	@Transactional
	public ProductDTO createProduct(ProductDTO productDTORequest) {
		Product product = ProductMapper.INSTANCE.productDTOtoProduct(productDTORequest);
		Product newProduct = productRepository.save(product);
		return ProductMapper.INSTANCE.productToProductDTO(newProduct);
	}

}
