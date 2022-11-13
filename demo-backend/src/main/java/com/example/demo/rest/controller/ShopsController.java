package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserGetDTO;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entity.Product;
import com.example.demo.entity.Profile;
import com.example.demo.entity.Shop;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.IProductService;
import com.example.demo.service.IShopService;
import com.example.demo.service.IUserService;
import com.example.demo.utils.Constant;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(ShopsController.REQUEST_MAPPING)
public class ShopsController {
	public static final String REQUEST_MAPPING = "shops";
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopsController.class);

	@Autowired
	private IShopService shopService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductService productService;

	/**
	 * Devuelve las ultimas 5 tiendas registradas ordenadas por id
	 * 
	 * @return
	 */

	@GetMapping(path = "/getShopsLastShop")
	public @ResponseBody List<ShopDTO> findAll() {
		LOGGER.info("find stores in progress...");
		return shopService.lastStores();
	}

	// GETALLSHOPS + PAGINATOR
	@PostMapping(path = "/getShopsPag", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataSourceRESTResponse<List<ShopDTO>> getShopsPag(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getShopsPag in progress...");
		DataSourceRESTResponse<List<ShopDTO>> dres = new DataSourceRESTResponse<>();
		try {
			dres = shopService.getShops(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			dres.setResponseMessage(e.getMessage());
		}
		LOGGER.info("getShopsPag is finished...");
		return dres;
	}

	@GetMapping("/getShop")
	public ResponseEntity<?> getShops(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getShop in progress...");
		ShopDTO shop = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;
		try {
			shop = shopService.getShopComplete(id);
			if (shop == null) {
				response.put(Constant.MESSAGE, Constant.SHOP_NOT_EXISTS);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				re = new ResponseEntity<ShopDTO>(shop, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage());
			re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("getProducts is finished...");
		return re;
	}

	@PostMapping(path = "/createShop")
	// @PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> createShop(@Valid @RequestBody ShopDTO createShopRequest, BindingResult result) {
		ShopDTO shopNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.SHOP_CREATED;
		if (!result.hasErrors()) {
			try {
				User newUser = createShopRequest.getUser();
				UserDTO newUserDTO = UserMapper.INSTANCE.userToUserDTO(newUser);
				Profile newProfile = new Profile(3);
				newUserDTO.getProfiles().clear();
				newUserDTO.getProfiles().add(newProfile);
				newUserDTO = userService.createUser(newUserDTO);
				shopNew = shopService.createShop(createShopRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());

			} catch (DataAccessException e) {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			}

			response.put("shop", shopNew);
		} else {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
			message = Constant.SHOP_NOT_CREATED;
			response.put(Constant.ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}
		response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
		response.put(Constant.MESSAGE, message); // Meter en todos los controller
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	// DELETE

	@DeleteMapping("/deleteShop")
	// @PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> deleteShop(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteShop in progress...");
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		String message = Constant.SHOP_DELETE_SUCCESS;
		try {
			shopService.deleteShop(id);
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
		} catch (DataAccessException e) {
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			status = HttpStatus.BAD_REQUEST;
			message = Constant.SHOP_NOT_DELETE;
		}
		response.put(Constant.MESSAGE, message);
		LOGGER.info("deleteShop is finished...");
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	// EDIT

	@PostMapping(path = "/editShop", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> editShop(@Valid @RequestBody ShopDTO editShopRequest, BindingResult result) {
		LOGGER.info("editShop in progress...");
		int id = 0;
		ShopDTO shopOlder = shopService.getShopComplete(editShopRequest.getId());

		// Como la ID del usuario no se recibe por el formulario, se le pone
		// directamente con el siguiente if
		if (shopOlder.getUser() != null) {
			editShopRequest.setUser(shopOlder.getUser());
		}
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.SHOP_EDIT_SUCCESS;
		if (shopOlder != null) {
			if (!result.hasErrors()) {
				try {
					id = shopService.editShop(editShopRequest);
					response.put("shopid", id);
					response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				} catch (DataAccessException e) {
					if (e.getMostSpecificCause().getMessage().contains(Constant.PHONE_ERROR)) {
						message = Constant.PHONE_ALREADY_EXISTS;
						status = HttpStatus.OK;
					} else {
						message = Constant.DATABASE_QUERY_ERROR;
						status = HttpStatus.BAD_REQUEST;
					}
					response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
					response.put(Constant.ERROR,
							e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				}

			} else {
				List<String> errors = new ArrayList<>();
				for (FieldError error : result.getFieldErrors()) {
					errors.add(error.getDefaultMessage());
				}
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
				message = Constant.SHOP_NOT_EDIT;
				response.put(Constant.ERROR, errors);
				status = HttpStatus.OK;
			}
		} else {
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			message = Constant.ID_NOT_EXISTS;
			status = HttpStatus.BAD_REQUEST;
		}

		response.put(Constant.MESSAGE, message);
		LOGGER.info("editShop is finished...");
		return new ResponseEntity<Map<String, Object>>(response, status);

	}

	@PostMapping(path = "/createProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createProduct(@RequestBody Product product, String login) {

		ProductDTO newProduct = null;
		UserDTO user = null;
		Shop shop = null;
		Map<String, Object> response = new HashMap<>();
		LOGGER.info("Create Product in progress...");
		try {
			user = userService.findByLogin("demoadmin");
			shop = shopService.getShopByUser(user);
			product.setShop(shop);
			newProduct = productService.createProductStore(product);
		} catch (DataAccessException e) {
		      response.put("message", "Error al realizar el insert en la base de datos");
		      response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
		      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    response.put("message", "Se registro correctamente al cliente");
		response.put("productId", newProduct.getId());
		response.put("storeId", newProduct.getShop().getId());
		LOGGER.info("Create Product is finish...");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/getShopByUser/{query}")
	@ResponseStatus(HttpStatus.OK)
	public List<ShopDTO> findByUser(@PathVariable Integer query) {
		LOGGER.info("search in progress...", query);

		return shopService.findByUserId(query);

	}

}
