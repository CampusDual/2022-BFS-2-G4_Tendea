/**
  * @author adolfob
 */
package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Cart;

/**
 * Mapper for Shopping Cart
 * @author adolfob
 */
@Mapper
public interface CartMapper {
	
	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
	
	/**
	 * Cart to CartDTO
	 * @param cart
	 * @return cartDTO
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	CartDTO cartToCartDTO(Cart cart);
	
	/**
	 * List of Cart to List CartDTO
	 * @param carts
	 * @return List<carDTO>
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	List<CartDTO> cartListToCartDTOList(List<Cart> carts);
	
	/**
	 * CarDTO to Cart
	 * @param cartDTO
	 * @return Cart
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	Cart cartDTOtoCart(CartDTO cartDTO);

}
