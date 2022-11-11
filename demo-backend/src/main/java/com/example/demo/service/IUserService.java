package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserGetDTO;
import com.example.demo.entity.User;

public interface IUserService {

//	Boolean canLogin(String user);
	
	List<UserGetDTO> findAll();
	
	UserDTO createUser(UserDTO createUserRequest);
	
	List<UserGetDTO> findByLoginContainingIgnoreCase(String query);
	
	UserDTO findByLogin(String login);
	
}
