package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface IUserService {

//	Boolean canLogin(String user);
	
	List<User> findAll();
	
	User createUser();
}
