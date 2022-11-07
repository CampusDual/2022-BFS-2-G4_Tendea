package com.example.demo.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IContactService;
import com.example.demo.utils.Constant;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(CategoriesController.REQUEST_MAPPING)
public class CategoriesController {

	public static final String REQUEST_MAPPING = "categories";
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);

	@Autowired
	private ICategoryService categoryService;

	/**
	 * Devuelve todos los categoriass que se encuentran en la tabla
	 * 
	 * @return categorias que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	@GetMapping(path = "/getCategories")
	// @PreAuthorize("hasAnyAuthority('CONTACTS')")
	public @ResponseBody List<CategoryDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return categoryService.findAll();
	}

	/**
	 * Obtiene una categoria de la base de datos por su id
	 * 
	 * @return
	 */
	
	@GetMapping("/getCategory")
	public ResponseEntity<?> getCategory(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getCategory in progress...");
		CategoryDTO category = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;

		try {
			category = categoryService.getCategory(id);
			if (category == null) {
				response.put(Constant.MESSAGE, Constant.CONTACT_NOT_EXISTS);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				re = new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage());
			re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("getContact is finished...");
		return re;
	}

}
