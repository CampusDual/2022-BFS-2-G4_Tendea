/**
 * @author adolfob
 */
package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.CartDTO;
import com.example.demo.dto.ContactDTO;
import com.example.demo.rest.response.DataSourceRESTResponse;

/**
 * CartService
 * @author adolfob
 *
 */

public interface ICartService {
	
	/**
	 * All cars 
	 */
	List<CartDTO> findAll();
	
	/**
	 * a cart from id
	 * @param id
	 * @return cartDTO
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	CartDTO getCart(Integer id);
	
	/**
	 * Delete a car from a BD
	 * @param id
	 * @return id
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	Integer deleteCart(Integer id);
	
	
	/**
	 * Create a cart
	 * @param createCartRequest
	 * @return CartDTO
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	CartDTO createCart(CartDTO createCartRequest);
	
	/**
	 * get my carts
	 * @param pageFilter
	 * @return
	 *
	 * @Since 20 nov 2022
	 * @author adolfob
	 */
	List<CartDTO> getMyCars(String user);
	

}
