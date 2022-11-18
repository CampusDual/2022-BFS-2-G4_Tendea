/**
 * @author adolfob
 */
package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author adolfob
 *
 */
public class CartDTO {
	
	private Integer id;

	private String comment;

	private Date createAt;

	private Date updatedAt;

//	@JsonIgnoreProperties({ "invoice", "hibernateLazyInitializer", "handler" })
//	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "catery_id") // crea la llave foranea en la otra tabla
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<CartItem> items;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getItems() {
		return this.items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	
	

	

}
