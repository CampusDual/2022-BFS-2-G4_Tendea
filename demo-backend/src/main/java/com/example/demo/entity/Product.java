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

@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	//: TODO: Quedan pendientes las categorias y la relacion OneToMany

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
	
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "category_id")
	@ManyToOne
	private Category category;
	
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

	public Product(Integer id2, String name2, Double price2, Date createAt2, List<ProductImage> images2,
			Double discount2, Integer bulk2, Category category) {
		this.id = id2;
		this.name = name2;
		this.price = price2;
		this.createAt = createAt2;
		this.images = images2;
		this.discount = discount2;
		this.bulk = bulk2;
		this.category = category;
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

//	public Date getUpdateAt() {
//		return this.updateAt;
//	}
//
//	public void setUpdateAt(Date updateAt) {
//		this.updateAt = updateAt;
//	}

	public Integer getBulk() {
		return this.bulk;
	}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
	
    private static final long serialVersionUID = 4L;
	
}
