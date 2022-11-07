package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ShopGetDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Shop;

@Mapper
public interface ShopGetMapper {

    ShopGetMapper INSTANCE = Mappers.getMapper( ShopGetMapper.class );
    
    ShopGetDTO shopToShopGetDTO(Shop shop);
    
    List<ShopGetDTO> shopToShopGetDTOList(List<Shop> shops);
    
    Shop shopGetDTOtoShop(ShopGetDTO shopDTO);
}
