package com.example.demo.rest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserGetDTO;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.Profile;
import com.example.demo.entity.Shop;
import com.example.demo.entity.ShopImage;
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
	@ResponseStatus(HttpStatus.CREATED)
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
//                shopNew.setUser(newUser);
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

	/**
	 * Crear un producto desde una tienda
	 * @param product
	 * @param login
	 * @return
	 */
	@PostMapping(path = "/createProduct")
	//@PreAuthorize("hasAnyAuthority('SHOPS')")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO createProductRequest, String login, BindingResult result) {
		LOGGER.info("createProduct in progress...");
		ProductDTO newProduct = null;
		Shop shop = null;
		UserDTO user;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.PRODUCT_CREATE_SUCCESS;
		
		if (!result.hasErrors()) {
			try {
				shop = shopService.getShopByUser("demoadmin");
				newProduct.setShop(shop);
				newProduct = productService.createProductStore(createProductRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
			} catch (DataAccessException e) {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			}
			response.put("id", newProduct.getId());
		} else {
			List<String> errors = new ArrayList<>();
			for(FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
			message = Constant.PRODUCT_NOT_CREATED;
			response.put(Constant.ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}
		LOGGER.info("Create Product is finish...");
		response.put(Constant.MESSAGE, "Producto registrado correctamente");
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("/getShopByUser/{query}")
	@ResponseStatus(HttpStatus.OK)
	public List<ShopDTO> findByUser(@PathVariable Integer query) {
		LOGGER.info("search in progress...", query);

		return shopService.findByUserId(query);

	}

	/**
	 * Subida de imagen de una tienda
	 * @param file
	 * @param id
	 * @return
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		ShopDTO shop = shopService.getShopComplete(id);

		if (shop == null) {
			response.put("message", Constant.SHOP_NOT_EXISTS); //
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		LOGGER.info("upload image in progress...", shop);
		if (!file.isEmpty()) {
			// Para que el nombre sea unico, agregamos un UUID al nombre de la imagen y
			// quitamos los espacios en blanco
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");

			// Ruta a la que se sube la imagen
			Path fileRoute = Paths.get("uploads").resolve(fileName).toAbsolutePath();

			try {
				Files.copy(file.getInputStream(), fileRoute);
			} catch (IOException e) {
				response.put("message", Constant.IMAGE_NOT_EXISTS);
				response.put("error", e.getMessage().concat(" :").concat(e.getCause().getMessage()));
				e.printStackTrace();
			}

			ShopImage shopImg = new ShopImage();
			shopImg.setName(fileName);
			shopImg.setUrl(fileName);

			try {
				shop.getImages().add(shopImg);
				shopService.createShop(shop);
				response.put("shop", shop);
				response.put("message", Constant.IMAGE_UPLOADED);
			} catch (Exception e) {
				response.put("message", Constant.IMAGE_UPLOAD_ERROR); // TRANSLATE
				response.put("error", e.getMessage().concat(" :").concat(e.getCause().getMessage()));
				e.printStackTrace();
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}

			LOGGER.info("upload image is finished...");

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/uploads/img/{photo:.+}") // Indica que va a recobir un parametro .extencion .jpg
	public ResponseEntity<Resource> showPhoto(@PathVariable String photo) {
		LOGGER.info("show image in progress...", photo);
		Path fileRoute = Paths.get("uploads").resolve(photo).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(fileRoute.toUri());
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("No se pudo cargar la imagen: " + photo);
		}

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		LOGGER.info("show image finish...", photo);
		return new ResponseEntity<Resource>(recurso, header, HttpStatus.OK);
	}

}
