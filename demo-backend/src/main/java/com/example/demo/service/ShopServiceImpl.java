package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.mapper.ShopGetMapper;
import com.example.demo.dto.mapper.ShopMapper;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entity.Shop;
import com.example.demo.entity.User;
import com.example.demo.repository.ShopRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;

@Service
public class ShopServiceImpl extends AbstractShopService implements IShopService {

	@Autowired
	private ShopRepository shopRepository;

//    @Override
//    public ShopDTO getShop(Integer id) {
//        Shop shop= shopRepository.findById(id).orElse(null);  
//        return ShopMapper.INSTANCE.shopToShopDto(shop);
//    }

	@Override
	public List<ShopDTO> findAll() {
		List<Shop> lShops = shopRepository.findAll();
		return ShopMapper.INSTANCE.shopToShopDTOList(lShops);
	}

	@Override
	public ShopGetDTO getShop(Integer id) {
		Shop shop = shopRepository.findById(id).orElse(null);
		return ShopGetMapper.INSTANCE.shopToShopGetDTO(shop);
	}

	@Override
	public ShopDTO getShopComplete(Integer id) {
		Shop shop = shopRepository.findById(id).orElse(null);
		return ShopMapper.INSTANCE.shopToShopDTO(shop);
	}

	@Override
	@Transactional
	public ShopDTO createShop(ShopDTO createShopRequest) {
		Shop shop = ShopMapper.INSTANCE.shopDTOtoShop(createShopRequest);
		Shop newShop = shopRepository.save(shop);
		return ShopMapper.INSTANCE.shopToShopDTO(newShop);
	}

	@Override
	@Transactional
	public Integer deleteShop(Integer id) {
		shopRepository.deleteById(id);
		return id;
	}

	@Override
	public Integer editShop(ShopDTO editShopRequest) {
		Shop shopFromDTO = ShopMapper.INSTANCE.shopDTOtoShop(editShopRequest);
		Shop editShop = shopRepository.save(fromEditShopRequest(shopFromDTO));
		return editShop.getId();
	}

	@Override
	@Transactional
	public DataSourceRESTResponse<List<ShopDTO>> getShops(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Shop> shops = SpecificationBuilder.selectDistinctFrom(shopRepository).where(pageFilter)
				.findAll(pageFilter);
		DataSourceRESTResponse<List<ShopDTO>> datares = new DataSourceRESTResponse<>();
		datares.setTotalElements((int) shops.getTotalElements());
		List<ShopDTO> lShopDTO = ShopMapper.INSTANCE.shopToShopDTOList(shops.getContent());
		datares.setData(lShopDTO);
		return datares;
	}

	/**
	 * Busca la tiendas por usuario
	 */
//	@Override
//	public List<ShopDTO> getShopByUser(UserDTO userDTO) {
//		User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
//		List<Shop> shop =  shopRepository.findByUser(user);
//		ShopDTO shopDTO = ShopMapper.INSTANCE.shopToShopDTO(shop);
//		return shopDTO;
//	}

	@Override
	public List<ShopDTO> lastStores() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Busca de tiendas por usuario
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ShopDTO> findByUserId(Integer id) {
		List<ShopDTO> shops = new ArrayList<>();
		shops = ShopMapper.INSTANCE.shopToShopDTOList(shopRepository.findByUserId(id));
		return shops;
	}

	/**
	 * Busca una tienda por usuario
	 */
	@Override
	@Transactional(readOnly = true)
	public Shop getShopByUser(UserDTO userDTO) {
		User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
		Shop shop = shopRepository.findByUser(user);
		return shop;
	}

	public List<ShopDTO> getShopsByUser(UserDTO userDTO) {
		User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
		List<Shop> shops =  shopRepository.findAllByUser(user);
		List<ShopDTO> lShops =  ShopMapper.INSTANCE.shopToShopDTOList(shops);
		return lShops;
	}


}
