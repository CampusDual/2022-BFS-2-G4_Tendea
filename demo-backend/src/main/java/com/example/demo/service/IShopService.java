package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IShopService {
    
    List<ShopDTO> findAll();
    
    ShopGetDTO getShop(Integer id);
    
    ShopDTO getShopComplete(Integer id);

    ShopDTO createShop(ShopDTO createShopRequest);
    
    // ShopDTO createShop(ShopDTO createShopRequest);
    
    Integer deleteShop(Integer id);
    
    Integer editShop(ShopDTO editShopRequest);
    
    DataSourceRESTResponse<List<ShopDTO>> getShops(AnyPageFilter pageFilter);

}
