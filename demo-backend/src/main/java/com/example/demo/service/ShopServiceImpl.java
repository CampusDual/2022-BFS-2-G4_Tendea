package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.dto.mapper.ContactMapper;
import com.example.demo.dto.mapper.ProductMapper;
import com.example.demo.dto.mapper.ShopGetMapper;
import com.example.demo.dto.mapper.ShopMapper;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Product;
import com.example.demo.entity.Shop;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.ShopRepository;

@Service
public class ShopServiceImpl extends AbstractDemoService implements IShopService{

    @Autowired
    private ShopRepository shopRepository;
    
//    @Override
//    public ShopDTO getShop(Integer id) {
//        Shop shop= shopRepository.findById(id).orElse(null);  
//        return ShopMapper.INSTANCE.shopToShopDto(shop);
//    }
    
    @Override
    public List<ShopGetDTO> findAll() {
        List<Shop> lShops = shopRepository.findAll();
        return ShopGetMapper.INSTANCE.shopToShopGetDTOList(lShops);
    }

    @Override
    public ShopGetDTO getShop(Integer id) {
        Shop shop = shopRepository.findById(id).orElse(null);
        return ShopGetMapper.INSTANCE.shopToShopGetDTO(shop);
    }

    @Override
    @Transactional
    public ShopDTO createShop(ShopDTO createShopRequest) {
        Shop shop = ShopMapper.INSTANCE.shopDTOtoShop(createShopRequest);
        Shop newShop = shopRepository.save(shop);
        return ShopMapper.INSTANCE.shopToShopDTO(newShop);
    }

//    @Override
//    public ShopDTO createShop(ShopDTO createShopRequest) {
//        // TODO Auto-generated method stub
//        return null;
//    }
}
