package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShopImage;
import com.example.demo.entity.User;

public class ShopDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    private String description;
    
    private Set<Category> categories = new HashSet<>(); 
    private Set<Product> products = new HashSet<>(); 
    private Set<ShopImage> images = new HashSet<>();
    
    private String address;
    private String city;
    private String email;
    private String phone;
    private String urlFB;
    private String urlInsta;
    
    private User user;
    
    private Integer activeStatus;
    
    
    
    // GETTERS
    
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getActiveStatus() {
        return activeStatus;
    }
    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
	public Set<ShopImage> getImages() {
		return images;
	}
	public void setImages(Set<ShopImage> images) {
		this.images = images;
	}
	public String getUrlFB() {
		return urlFB;
	}
	public void setUrlFB(String urlFB) {
		this.urlFB = urlFB;
	}
	public String getUrlInsta() {
		return urlInsta;
	}
	public void setUrlInsta(String urlInsta) {
		this.urlInsta = urlInsta;
	}
    
   
    
}
