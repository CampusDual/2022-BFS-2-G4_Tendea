package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;

import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IShopService {
	
	/**
	 * Obtiene las ultimas tiendas registradas
	 * @return
	 */
	List<ShopDTO> lastStores();
    
    List<ShopDTO> findAll();
    
    ShopGetDTO getShop(Integer id);
    
    ShopDTO getShopComplete(Integer id);

    ShopDTO createShop(ShopDTO createShopRequest);
    
    Integer deleteShop(Integer id);
    
    Integer editShop(ShopDTO editShopRequest);
    
    DataSourceRESTResponse<List<ShopDTO>> getShops(AnyPageFilter pageFilter);
    
    List<ShopDTO> findByUserId(Integer id);

}
