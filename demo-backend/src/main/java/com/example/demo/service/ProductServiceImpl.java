package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.mapper.ContactMapper;
import com.example.demo.dto.mapper.ProductMapper;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;

@Service
public class ProductServiceImpl extends AbstractProductService implements IProductService {
	
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


	@Override
	public ProductDTO getProduct(Integer id) {
		Product product = productRepository.findById(id).orElse(null);
		return ProductMapper.INSTANCE.productToProductDTO(product);
	}

	@Override
	public Integer deleteProduct(Integer id) {
		productRepository.deleteById(id);
		return id;
	}

	/**
	 * Devuleve el DTO paginado
	 */
	
	@Override
	@Transactional
	public DataSourceRESTResponse<List<ProductDTO>> getProducts(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Product> products = SpecificationBuilder.selectDistinctFrom(productRepository).where(pageFilter)
				.findAll(pageFilter); 
		DataSourceRESTResponse<List<ProductDTO>> datares = new DataSourceRESTResponse<>();
		datares.setTotalElements((int) products.getTotalElements());
		List<ProductDTO> lProductDTO = ProductMapper.INSTANCE.productToProductDTOList(products.getContent());
		datares.setData(lProductDTO);
		return datares;
	}

	/**
	 * Edita un producto con el id (Esto viene de @AbstractProductService)
	 */
	@Override
	public Integer editProduct(ProductDTO editProductRequest) {
		Product productFromDTO = ProductMapper.INSTANCE.productDTOtoProduct(editProductRequest); 
		Product editProduct = productRepository.save(fromEditProductRequest(productFromDTO));
		return editProduct.getId();
	}
	
	
	
	
	
	
	
	
	
	
	

}
