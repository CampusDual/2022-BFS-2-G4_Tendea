package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );
	
	ProductDTO productToProductDTO(Product product);
	
	List<ProductDTO> productToProductDTOList(List<Product> products);
	
	Product productDTOtoProduct(ProductDTO productDTO);

}
