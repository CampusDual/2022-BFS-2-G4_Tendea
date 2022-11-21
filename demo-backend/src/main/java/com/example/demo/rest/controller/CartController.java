/**
 * @author adolfob
 */
package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.CartDTO;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.ICartService;
import com.example.demo.utils.Constant;

/**
 * Cart Rest Controller
 * 
 * @author adolfob
 *
 */

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(CartController.REQUEST_MAPPING)
public class CartController {

	public static final String REQUEST_MAPPING = "carts";
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private ICartService cartService;

	/**
	 * Obtiene los carritos de un cliente por id
	 * 
	 * @param id
	 *
	 * @Since 20 nov 2022
	 * @author adolfob
	 */
	@GetMapping(path = "/getMyCars/{user}")
	@PreAuthorize("hasAnyAuthority('CLIENTS')")
	public ResponseEntity<?> getMycars(@PathVariable String user) {
		LOGGER.info("getMyCarts in progress...", user);
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;
		List<CartDTO> cars = new ArrayList<>();
		try {
			cars = cartService.getMyCars(user);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage());
			re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (cars.isEmpty()) {
			response.put(Constant.MESSAGE, Constant.CARTS_NOT_EXIST);
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			response.put("cars", cars);
			re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
			re = new ResponseEntity<CartDTO>(HttpStatus.OK);
		}

		return re;
	}
	
	@GetMapping(path = "/create/{user}")
	@PreAuthorize("hasAnyAuthority('CLIENTS')")


}
