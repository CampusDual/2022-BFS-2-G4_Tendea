package com.example.demo.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4201"} )
@RestController
@RequestMapping(ShopImagesController.REQUEST_MAPPING)
public class ShopImagesController {
	
	public static final String REQUEST_MAPPING = "shopImgs";

}
