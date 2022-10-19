package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.IProductService;
import com.example.demo.utils.Constant;


@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(ProductsRestController.REQUEST_MAPPING)
public class ProductsRestController {
	public static final String REQUEST_MAPPING = "products";
	
	@Autowired
	private IProductService productService;
	
	
	//Listado con paginación
	@PostMapping(path= "/getProducts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataSourceRESTResponse<List<ProductDTO>> getProducts(@RequestBody AnyPageFilter pageFilter ) {
		DataSourceRESTResponse<List<ProductDTO>> dres = new DataSourceRESTResponse<>();
		try {
			dres = productService.getProducts(pageFilter);
		} catch (DemoException e) {
			dres.setResponseMessage(e.getMessage());
		}
		return dres;
	}
	
	
	
	@PostMapping(path = "createProduct")
	@ResponseStatus(HttpStatus.CREATED)
//	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO createProductRequest, BindingResult result) {
		ProductDTO productNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = "PRODUCTO CREADO";
		if(!result.hasErrors()) {
			try {
				productNew = productService.createProduct(createProductRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				
			} catch (DataAccessException e) {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			}
			
			response.put("producto", productNew);
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
		
		
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	
	
	
	

}
