package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ShopImage;
import com.example.demo.repository.ShopImageRepository;

@Service
public class ShopImageServiceImpl implements IShopImageService {
	
	@Autowired
	private ShopImageRepository shopImgRepository;
	
	@Override
	public ShopImage getShopImage(Integer id) {
		ShopImage shopImage = shopImgRepository.findById(id).orElse(null);
		return shopImage;
	}
	
	@Override
	public Integer deleteShopImage(Integer id) {
		shopImgRepository.deleteById(id);
		return id;
	}

}
