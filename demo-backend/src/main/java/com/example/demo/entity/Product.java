package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.example.demo.utils.Constant;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = -2189993412812655677L;
	
	//Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProductImage> images = new HashSet<>();
	
	@NotEmpty(message = Constant.NAME_REQUIRED)
	@Column(nullable = false, name="product_name")
	private String name;
	
	
//	private Vendor vendor;
	
	@Column(nullable = false)
	private Float price;
	
	@Column()
	private Float discount;
	
//	private Integer isUnitary;
//	private String productDescription;
//	private String[] otherImg;
//	private Date createdAt;
	
	// Getters & Setters
	
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
	
	public Set<ProductImage> getImages() {
		return images;
	}

	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}
	
	
	
	//Constructors

	public Product() {
		super();
	}
	
	public Product(Set<ProductImage> images, String name, Float price, Float discount) {
		this.images = images;
		this.name = name;
		this.price = price;
		this.discount = discount;
		
	}
	
	public static Product from(Set<ProductImage> querySet, String query, Float number) {
		return new Product(querySet, query, number, number);
	}
	
	
	//Methods
	
    public void addImage(ProductImage image) {
        if (null == images) {
            images = new HashSet<>();
        }
        images.add(image);
        image.setId(this.id);
    }
	
	
	
	
	
	

}
