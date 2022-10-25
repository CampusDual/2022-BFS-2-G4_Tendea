package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.event.AncestorEvent;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.example.demo.entity.ProductImage;
import com.example.demo.utils.Constant;
import com.google.gson.annotations.Since;

import com.example.demo.entity.ProductImage;


public class ProductDTO {
	public void setBulk(Integer bulk) {
		this.bulk = bulk;
	}

	/**
	 * Esto va sin las relaciones (Buble infinito)
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private List<ProductImage> images;

	private String name;

	private Double price;
	
	private Double discount;
	
	private Integer bulk;
	
	private String description;
	
	private Date createAt;

//	private Date updateAt;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProductImage> getImages() {
		return this.images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public Integer getBulk() {
		return this.bulk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

//	public Date getUpdateAt() {
//		return this.updateAt;
//	}
//
//	public void setUpdateAt(Date updateAt) {
//		this.updateAt = updateAt;
//	}
	
	


}
