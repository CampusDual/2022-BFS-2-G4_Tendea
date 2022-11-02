package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;

public interface IShopService {
    
    List<ShopGetDTO> findAll();
    
    ShopGetDTO getShop(Integer id);

    ShopDTO createShop(ShopDTO createShopRequest);
    
    // ShopDTO createShop(ShopDTO createShopRequest);

}
