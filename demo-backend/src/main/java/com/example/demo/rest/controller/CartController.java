/**
 * @author adolfob
 */
package com.example.demo.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cart Rest Controller
 * @author adolfob
 *
 */

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(ContactsController.REQUEST_MAPPING)
public class CartController {
	
	public static final String REQUEST_MAPPING = "carts";
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
	
	
	

}
