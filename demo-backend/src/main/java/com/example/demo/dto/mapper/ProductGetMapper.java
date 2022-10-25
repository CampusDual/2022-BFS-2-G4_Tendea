package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductGetDTO;
import com.example.demo.dto.UserGetDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

@Mapper
public interface ProductGetMapper {
    
    
    ProductGetMapper INSTANCE = Mappers.getMapper( ProductGetMapper.class );
    
    ProductGetDTO productToProductGetDTO(Product product);
    
    List<ProductGetDTO> productToProductGetDTOList(List<Product> products);
    
    Product productGetDTOtoProduct(ProductGetDTO productDTO);

}
