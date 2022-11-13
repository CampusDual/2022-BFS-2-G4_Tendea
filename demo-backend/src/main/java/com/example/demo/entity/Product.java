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
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	// : TODO: Quedan pendientes las categorias y la relacion OneToMany

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id") // crea la llave foranea en la otra tabla
	private List<ProductImage> images;

	@NotEmpty(message = "falta name")
	@Column(nullable = false, name = "product_name")
	private String name;

	@Column(nullable = false)
	private Double price;

	private Double discount;

	@Column(nullable = false)
	private Integer bulk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	private Shop shop;

	public void setBulk(Integer bulk) {
		this.bulk = bulk;
	}

	@Column()
	private String description;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

//	@Column(name = "updated_at")
//	@Temporal(TemporalType.DATE)
//	private Date updateAt;

	public Product() {
		this.images = new ArrayList<>();
	}
	
	public Product(Integer id, String name, Double price, Date createAt, List<ProductImage> images,
			Double discount, Integer bulk, Category category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.createAt = createAt;
		this.images = images;
		this.discount = discount;
		this.bulk = bulk;
		this.category = category;
	}

	/** Constructor con tienda **/
	public Product(Integer id, String name, Double price, Date createAt, List<ProductImage> images,
			Double discount, Integer bulk, Category category, Shop shop) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.createAt = createAt;
		this.images = images;
		this.discount = discount;
		this.bulk = bulk;
		this.category = category;
		this.shop = shop;
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	// Getters & Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBulk() {
		return this.bulk;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	private static final long serialVersionUID = 4L;

}
