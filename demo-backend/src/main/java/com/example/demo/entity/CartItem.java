/**
 * @author adolfob
 */
package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Items of cart
 * @author adolfob
 *
 */

@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	

	/**
	 * Getters & Setters
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * Calcular el total
	 * @author adolfob
	 * @return
	 */
	public Double getAmount() {
		return this.quantity.doubleValue() * this.product.getPrice();
	}


}
