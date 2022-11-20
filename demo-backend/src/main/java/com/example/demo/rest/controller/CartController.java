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
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.ICartService;

/**
 * Cart Rest Controller
 * @author adolfob
 *
 */

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(CartController.REQUEST_MAPPING)
public class CartController {
	
	public static final String REQUEST_MAPPING = "carts";
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private ICartService cartService;
	
	
	
	/**
	 * Obtiene los carritos de un cliente por id
	 * @param id
	 *
	 * @Since 20 nov 2022
	 * @author adolfob
	 */
	@GetMapping(path = "/getMyCars/{user}")
	public ResponseEntity<?> getMycars(@PathVariable String user) {
		Map<String, Object> response = new HashMap<>();
		List<CartDTO> cars = new ArrayList<>();
		try {
			cars = cartService.getMyCars(user);
		} catch (DataAccessException e) {
		      response.put("message", "Error al realizar la consulta en la base de datos");
		      response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
		}
		
	    if (cars.isEmpty()) {
	        response.put("message", "El cliente: ".concat(user.toString()).concat(" no tiene carritos"));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	      }
	
		return new ResponseEntity<CartDTO>(HttpStatus.OK);
	}

}
