package com.example.demo.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(UserRestController.REQUEST_MAPPING)
public class UserRestController {
	public static final String REQUEST_MAPPING = "users";
	
	
	
	@Autowired
	private IUserService userService;
	
	
	@GetMapping(path = "/getUsers")
	public @ResponseBody List<User> findAll() {
		return userService.findAll();
	}
	
	@PostMapping(path = "createUser")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user) {
		return userService.createUser();
	}
	
	
	
	
	
	
	
	
	
	
	

}
