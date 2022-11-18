/**
 * @author adolfob
 */
package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Shopping Cart Class
 * 
 * @author adolfob
 *
 */
@Entity
@Table(name = "carts")
public class Cart implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String comment;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.DATE)
	private Date updatedAt;

	@JsonIgnoreProperties({ "invoice", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "catery_id") // crea la llave foranea en la otra tabla
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<CartItem> items;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

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

	public User getClient() {
		return this.user;
	}

	public void setClient(User user) {
		this.user = user;
	}

	public List<CartItem> getItems() {
		return this.items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public Cart() {
		items = new ArrayList<>();
	}
	
	public Cart(Integer id, String comment, Date createAt, Date updatedAt, User user, List<CartItem> items) {
		this.id = id;
		this.comment = comment;
		this.createAt = createAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.items = items;
	}

	/** Metodos propios **/

	/**
	 * Calcular el total del valor del carrito
	 * 
	 * @return
	 *
	 * @Since 17 nov 2022
	 * @author adolfob
	 */
	public Double getTotal() {
		Double total = 0.00;
		for (CartItem cartItem : items) {
			total += cartItem.getAmount();
		}
		return total;
	}

	private static final long serialVersionUID = 1L;

}
