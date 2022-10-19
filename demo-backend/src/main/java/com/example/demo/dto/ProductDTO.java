package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

import com.example.demo.entity.ProductImage;

public class ProductDTO {
	
	//Attributes
	
	private Integer id;
	
	private String name;
	
	private Float price;
	
	private Float discount;
	
	private Set<ProductImage> images = new HashSet<>();

	
	//Getters & Setters
	
	public Set<ProductImage> getImages() {
		return images;
	}

	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	

	
	

}
