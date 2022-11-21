package com.example.demo.service;

import com.example.demo.entity.ShopImage;

public interface IShopImageService {
	
	ShopImage getShopImage(Integer id);
	
	Integer deleteShopImage(Integer id);

}
