package com.example.demo.rest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.mapper.ProductMapper;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.IProductService;
import com.example.demo.utils.Constant;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(ProductsController.REQUEST_MAPPING)
public class ProductsController {
	public static final String REQUEST_MAPPING = "products";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private IProductService productService;

	/**
	 * Obtiene un producto de BDD con el id indicado.
	 * 
	 * @param id el id del contacto de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	@GetMapping("/getProduct")
	@PreAuthorize("hasAnyAuthority('CLIENTS')") // TODO: Debemos poner los roles correctos
	public ResponseEntity<?> getProducts(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getContact in progress...");
		ProductDTO product = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;
		try {
			product = productService.getProduct(id);
			if (product == null) {
				response.put(Constant.MESSAGE, Constant.CONTACT_NOT_EXISTS);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				re = new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
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

	@PostMapping(path = "/getProducts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@PreAuthorize("hasAnyAuthority('CLIENTS')")
	public @ResponseBody DataSourceRESTResponse<List<ProductDTO>> getProducts(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getProducts in progress...");
		DataSourceRESTResponse<List<ProductDTO>> dres = new DataSourceRESTResponse<>();
		try {
			dres = productService.getProducts(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			dres.setResponseMessage(e.getMessage());
		}
		LOGGER.info("getProducts is finished...");
		return dres;
	}

	/**
	 * Crea los productos
	 * 
	 * @param createProductRequest
	 * @param result
	 * @return
	 */

	@PostMapping(path = "createProduct")
	@ResponseStatus(HttpStatus.CREATED)
//	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO createProductRequest, BindingResult result) {
		ProductDTO productNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.PRODUCT_CREATED;
		if (!result.hasErrors()) {
			try {
				productNew = productService.createProduct(createProductRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());

			} catch (DataAccessException e) {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			}

			response.put("product", productNew);
		} else {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
			message = Constant.PRODUCT_NOT_CREATED;
			response.put(Constant.ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}

		response.put(Constant.MESSAGE, message); // Meter en todos los controller
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	/**
	 * Elimina un producto de la BD
	 * 
	 * @param id
	 * @return
	 */

	@DeleteMapping("/deleteProduct")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> deleteProduct(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteProduct in progress...");
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		String message = Constant.CONTACT_DELETE_SUCCESS;
		try {
			productService.deleteProduct(id);
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
		} catch (DataAccessException e) {
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			status = HttpStatus.BAD_REQUEST;
			message = Constant.CONTACT_NOT_DELETE;
		}
		response.put(Constant.MESSAGE, message);
		LOGGER.info("deleteProduct is finished...");
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	/**
	 * TODO: verificar esto Listado de los productos con paginador
	 */
	@GetMapping("/getAllProducts")
	public List<ProductDTO> getAllProducts() {
		return productService.findAll();
	}

	/**
	 * Edicion de un producto de la BD
	 */

	@PostMapping(path = "/editProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDTO editProductRequest, BindingResult result) {
		LOGGER.info("editProduct in progress...");
		int id;
		ProductDTO productOlder = productService.getProduct(editProductRequest.getId());
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.CONTACT_EDIT_SUCCESS;
		if (productOlder != null) {
			if (!result.hasErrors()) {
				try {
					id = productService.editProduct(editProductRequest);
					response.put("product_id", id);
					response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				} catch (DataAccessException e) {
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
				message = Constant.CONTACT_NOT_EDIT;
				response.put(Constant.ERROR, errors);
				status = HttpStatus.OK;
			}
		} else {
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			message = Constant.ID_NOT_EXISTS;
			status = HttpStatus.BAD_REQUEST;
		}

		response.put(Constant.MESSAGE, message);
		LOGGER.info("eeditProduct is finished...");
		return new ResponseEntity<Map<String, Object>>(response, status);

	}

	// Post Product with images

//	@PostMapping(path = "createProduct")
//	@ResponseStatus(HttpStatus.CREATED)
////	@PreAuthorize("hasAnyAuthority('CONTACTS')")
//	public ResponseEntity<?> createProductImg(@Valid @RequestBody ProductDTO createProductRequest, BindingResult result) {
//		ProductDTO productNew = null;
//		Map<String, Object> response = new HashMap<>();
//		HttpStatus status = HttpStatus.CREATED;
//		String message = "PRODUCTO CREADO";
//		if (!result.hasErrors()) {
//			try {
//				productNew = productService.createProduct(createProductRequest);
//				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
//
//			} catch (DataAccessException e) {
//				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
//				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			}
//
//			response.put("product", productNew);
//		} else {
//			List<String> errors = new ArrayList<>();
//			for (FieldError error : result.getFieldErrors()) {
//				errors.add(error.getDefaultMessage());
//			}
//			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
//			message = Constant.PRODUCT_NOT_CREATED;
//			response.put(Constant.ERROR, errors);
//			status = HttpStatus.BAD_REQUEST;
//		}
//
//		return new ResponseEntity<Map<String, Object>>(response, status);
//	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		ProductDTO product = productService.getProduct(id);

		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			Path fileRoute = Paths.get("uploads").resolve(fileName).toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), fileRoute);
			} catch (IOException e) {
				response.put("message", "Error al subir el logo del cliente el actualizar en la base de datos"); // Acordarse
																													// constante
																													// TRANSLATE
				response.put("error", e.getMessage().concat(" :").concat(e.getCause().getMessage()));
				e.printStackTrace();
			}

			ProductImage productImg = new ProductImage();
			productImg.setName(fileName);
			productImg.setUrl(fileName);

			product.getImages().add(productImg);
			productService.createProduct(product); // editProduct <-?
			response.put("product", product);
			response.put("message", "Imagen subida correctamente");
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	/**
	 * Busqueda de productos por la categoria
	 */
	@GetMapping("/getAllProductsByCategory/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProductsByCategory(@PathVariable Integer id) {
		return productService.findByCategory(id);
	}

	/**
	 * Busqueda de productos por el nombre
	 */
	@GetMapping("/getProductsByName/{query}, /getProductsByName")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProductsByName(@PathVariable String query) {
		LOGGER.info("search in progress...", query);

		if (!query.isBlank()) {
			return productService.findByNameContainingIgnoreCase(query);
		}
		return productService.findAll();

	}

}
