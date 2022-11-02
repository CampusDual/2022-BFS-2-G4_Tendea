package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Shop;

@Mapper
public interface ShopMapper {

    ShopMapper INSTANCE = Mappers.getMapper( ShopMapper.class );
    
    ShopDTO shopToShopDTO(Shop shop);
    
    List<ShopDTO> shopToShopDTOList(List<Shop> shops);
    
    Shop shopDTOtoShop(ShopDTO shopDTO);
}
