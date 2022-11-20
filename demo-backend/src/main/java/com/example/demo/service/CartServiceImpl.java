/**
 * @author adolfob
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.mapper.CartMapper;
import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;

/**
 * Implementacion del carrito
 * @author adolfob
 *
 */
@Service
public class CartServiceImpl  extends AbstractCartService implements ICartService {

	@Autowired
	private CartRepository cartRepository;
	
	/**
	 * Obtiene todos los carritos
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CartDTO> findAll() {
		List<Cart> carts = cartRepository.findAll();
		return CartMapper.INSTANCE.cartListToCartDTOList(carts);
	}

	@Override
	@Transactional(readOnly = true)
	public CartDTO getCart(Integer id) {
		Cart cart = cartRepository.findById(id).orElse(null);
		return CartMapper.INSTANCE.cartToCartDTO(cart);
	}

	@Override
	@Transactional
	public Integer deleteCart(Integer id) {
		cartRepository.deleteById(id);
		return id;
	}

	@Override
	@Transactional
	public CartDTO createCart(CartDTO createCartRequest) {
		Cart cart = CartMapper.INSTANCE.cartDTOtoCart(createCartRequest);
		Cart newCart = cartRepository.save(cart);
		return CartMapper.INSTANCE.cartToCartDTO(newCart);
	}

	/**
	 * Obtiene mis carritos paginados
	 */
	@Override
	@Transactional(readOnly= true)
	public List<CartDTO> getMyCars(String user) {
		List<Cart> carts = cartRepository.findAll();
		return CartMapper.INSTANCE.cartListToCartDTOList(carts);
	}


}
