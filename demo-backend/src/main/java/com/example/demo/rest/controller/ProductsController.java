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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.Shop;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IProductImageService;
import com.example.demo.service.IProductService;
import com.example.demo.service.IShopService;
import com.example.demo.service.IUserService;
import com.example.demo.utils.Constant;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(ProductsController.REQUEST_MAPPING)
public class ProductsController {
	public static final String REQUEST_MAPPING = "products";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private IProductService productService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IShopService shopService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductImageService imageService;

	/**
	 * Obtiene un producto de BDD con el id indicado.
	 * 
	 */
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<?> getProducts(@PathVariable Integer id) {
		LOGGER.info("getProduct in progress...", id);
		ProductDTO product = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;
		try {
			product = productService.getProduct(id);
			if (product == null) {
				response.put(Constant.MESSAGE, Constant.PRODUCT_NOT_EXISTS);
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
		LOGGER.info("getProduct is finished...", product);
		return re;
	}

	/**
	 * Obtiene los productos
	 * 
	 * @param pageFilter
	 * @return
	 *
	 * @Since 15 nov 2022
	 * @author adolfob
	 */
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
	 * Crea un producto desde el admin productos
	 * 
	 * @param createProductRequest
	 * @param result
	 * @return
	 */

	@PostMapping(path = "createProduct")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyAuthority('ADMIN', 'SHOP')")
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
	// @PreAuthorize("hasAnyAuthority('SHOPS')")
	public ResponseEntity<?> deleteProduct(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteProduct in progress...");
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		String message = Constant.PRODUCT_DELETE_SUCCESS;
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
	 * Crea un producto desde una tienda con el id del usuario
	 * 
	 * @param createProductStoreRequest
	 * @param login
	 * @param result
	 * @return
	 *
	 * @Since 15 nov 2022
	 * @author adolfob
	 */
	@PostMapping(path = "/createProduct/{login}")
	// @PreAuthorize("hasAnyAuthority('SHOPS')")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO createProductStoreRequest,
			@PathVariable(value = "login") String login, BindingResult result) {
		LOGGER.info("createProduct in progress...");
		ProductDTO newProduct = null;
		Shop shop = null;
		UserDTO user;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.PRODUCT_CREATE_SUCCESS;

		if (!result.hasErrors()) {
			try {
				user = userService.findByLogin(login);
				shop = shopService.getShopByUser(user);
				createProductStoreRequest.setShop(shop);
				newProduct = productService.createProductStore(createProductStoreRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
			} catch (DataAccessException e) {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			}
			response.put("id", newProduct.getId());
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
	 * Edición de un producto desde una tienda con el
	 * 
	 * @return
	 *
	 * @Since 15 nov 2022
	 * @author adolfob
	 */
	@PutMapping(path = "/editProduct/{id}/{login}")
	// @PreAuthorize("hasAnyAuthority('SHOPS')")
	public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDTO editProductRequest,
			@PathVariable(value = "id") Integer id, @PathVariable(value = "login") String login, BindingResult result) {
		LOGGER.info("editProduct in progress...");
		// Shop shop = null;
		UserDTO user;
		ProductDTO productOlder = productService.getProduct(id);
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.CONTACT_EDIT_SUCCESS;
		if (productOlder != null) {
			if (!result.hasErrors()) {
				try {
					Category category = categoryService.findById(editProductRequest.getCategory().getId());
					editProductRequest.setImages(productOlder.getImages());
					editProductRequest.setCreateAt(productOlder.getCreateAt());
					editProductRequest.setShop(productOlder.getShop());
					editProductRequest.setCategory(category);
					user = userService.findByLogin(login);
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

		// response.put(Constant.MESSAGE, message);
		LOGGER.info("editProduct is finished...");
		response.put(Constant.MESSAGE, "Producto editado correctamente");
		return new ResponseEntity<Map<String, Object>>(response, status);

	}

	/**
	 * Subir foto de producto, a la carpeta uploads, max 10MB
	 * 
	 * @param file
	 * @param id   http://localhost:9999/products/upload
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		ProductDTO product = productService.getProduct(id);

		if (product == null) {
			response.put("message", Constant.PRODUCT_NOT_EXISTS); // Acordarse
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		LOGGER.info("upload image in progress...", product);
		if (!file.isEmpty()) {
			// Para que el nombre sea unico, agregamos un UUID al nombre de la imagen y
			// quitamos los espacios en blanco
			String fileName = UUID.randomUUID().toString() + "_"
					+ file.getOriginalFilename().replace(" ", "").toLowerCase();

			// Ruta a la que se sube la imagen
			Path fileRoute = Paths.get("uploads").resolve(fileName).toAbsolutePath();

			try {
				Files.copy(file.getInputStream(), fileRoute);
			} catch (IOException e) {
				response.put("message", "Error al subir la imagen del producto"); // Acordarse // TRANSLATE
				response.put("error", e.getMessage().concat(" :").concat(e.getCause().getMessage()));
				e.printStackTrace();
			}

			/**
			 * si el producto tienes otras imagenes las elimina de la BD
			 */
			if (product.getImages().size() >= 0) {
				for (int i = 0; i < product.getImages().size(); i++) {
					imageService.deleteImage(product.getImages().get(i).getId());
				}
			}

			/**
			 * Agrega la nueva imagen al producto
			 */
			ProductImage productImg = new ProductImage();
			productImg.setName(fileName);
			productImg.setUrl(fileName);

			try {
				product.getImages().add(productImg);

				productService.createProduct(product);
				response.put("product", product);
				response.put("message", Constant.IMAGE_UPLOADED);
			} catch (Exception e) {
				response.put("message", Constant.IMAGE_UPLOAD_ERROR); // TRANSLATE // TRANSLATE
				response.put("error", e.getMessage().concat(" :").concat(e.getCause().getMessage()));
				e.printStackTrace();
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}

			LOGGER.info("upload image is finished...");

		}
		response.put("product", product);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * Mostrar foto de producto
	 */
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
			HttpHeaders header = new HttpHeaders();
			photo = "ImgNoAvailable.png";
			fileRoute = Paths.get("uploads").resolve(photo).toAbsolutePath();
			try {
				recurso = new UrlResource(fileRoute.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
			LOGGER.info("show image finish...", photo);
			return new ResponseEntity<Resource>(recurso, header, HttpStatus.OK);
		}

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		LOGGER.info("show image finish...", photo);
		return new ResponseEntity<Resource>(recurso, header, HttpStatus.OK);
	}

	/**
	 * Busqueda de productos por la categoria
	 */
	@GetMapping("/getAllProductsByCategory/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProductsByCategory(@PathVariable Integer id) {
		CategoryDTO cat = categoryService.getCategory(id);
		return productService.findByCategory(cat);
	}

	/**
	 * Busqueda de los productos por el nombre
	 * 
	 * @param query
	 * @return
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	@GetMapping("/getProductsByName/{query}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProductsByName(@PathVariable String query) {
		LOGGER.info("search in progress...", query);

		if (!query.isBlank()) {
			return productService.findByNameContainingIgnoreCase(query);
		}
		return productService.findAll();

	}

	// Get all the products from the selected shop
	@GetMapping("/getProductsByShopId/{shopId}")
	public List<ProductDTO> findByShop(@PathVariable Integer shopId) {
		LOGGER.info("search in progress...", shopId);

		return productService.findByShopId(shopId);

	}

	/**
	 * Obtiene los productos por el id para una tienda
	 * 
	 * @param id
	 * @param pageFilter
	 * @return
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	@PostMapping(path = "/getProductsByShop/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@PreAuthorize("hasAnyAuthority('CLIENTS')")
	public @ResponseBody DataSourceRESTResponse<List<ProductDTO>> getProductsByShop(@PathVariable Integer id,
			@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getProducts in progress...");
		DataSourceRESTResponse<List<ProductDTO>> dres = new DataSourceRESTResponse<>();
		try {
			dres = productService.findProductsByShopPag(id, pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			dres.setResponseMessage(e.getMessage());
		}
		LOGGER.info("getProducts is finished...");
		return dres;
	}

}
