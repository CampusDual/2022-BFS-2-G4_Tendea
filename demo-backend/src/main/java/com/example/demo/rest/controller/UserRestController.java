package com.example.demo.rest.controller;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.service.IUserService;
import com.example.demo.utils.Constant;

import org.slf4j.LoggerFactory;

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(UserRestController.REQUEST_MAPPING)
public class UserRestController {
	public static final String REQUEST_MAPPING = "users";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactsController.class);
	
	
	@Autowired
	private IUserService userService;
	
	
//	@GetMapping(path = "/getUsers")
//	public @ResponseBody List<User> findAll() {
//		return userService.findAll();
//	}
	
	@PostMapping(path = "createUser")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO createUserRequest, BindingResult result) {
		LOGGER.info("createContact in progress...");
		UserDTO userNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = "USUARIO CREADO";
		if(!result.hasErrors()) {
			try {
				userNew = userService.createUser(createUserRequest);	
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
			} catch (DataAccessException e) {
				if(e.getMostSpecificCause().getMessage().contains(Constant.PHONE_ERROR)) {
					message = Constant.PHONE_ALREADY_EXISTS;
					status= HttpStatus.OK;
				}else {
					message = Constant.DATABASE_QUERY_ERROR;
					status= HttpStatus.BAD_REQUEST;
				}
				
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				
			}
			response.put("usuario", userNew);
		}else {
			List<String> errors = new ArrayList<>();
			for(FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
			message = Constant.CONTACT_NOT_CREATED;
			response.put(Constant.ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}
		
		LOGGER.info("createContact is finished...");
		response.put(Constant.MESSAGE, message);
		
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	
	
	
	
	
	
	
	
	
	
	

}
