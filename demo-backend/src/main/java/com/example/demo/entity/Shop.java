package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.utils.Constant;

@Entity
@Table (name = "shops")
public class Shop implements Serializable {
    
    // --- ATTRIBUTES ---
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "shop_id") // crea la llave foranea en la otra tabla
	private List<ShopImage> images;
    
    @NotEmpty(message = Constant.NAME_REQUIRED)
    @Size(min = 2, max = 24, message = Constant.SHOP_INCORRECT_SIZE)
    @Column(nullable=false)
    private String name;
    
    @Column
    private String description;
    
    @ManyToMany
    @JoinTable(name = "shops_categories_map", joinColumns = { @JoinColumn(name = "shop_id") }, inverseJoinColumns = {
            @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<>(); 
    
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "shop_id")
//    private Set<Product> products = new HashSet<>(); 

    private String address;
    
    @NotNull(message = Constant.CITY_REQUIRED)
    @Column(nullable=false)
    private String city;
    
    @NotNull(message = Constant.PHONE_REQUIRED)
    @Column(nullable=false, unique = true)
    private String phone;
    
    @NotEmpty(message = Constant.EMAIL_REQUIRED)
    @Email(message= Constant.EMAIL_INVALID)
    @Column(nullable=false)
    private String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    //@NotEmpty(message = Constant.USER_ACTIVE_STATUS_REQUIRED)
    @Column(name = "active_status")
    private Integer activeStatus;
    
    @Column()
    private String urlFb;
    
    @Column()
    private String urlInsta;
    
    
    // --- CONSTRUCTORS ---
    
    public Shop() {
        super();
    }

    public Shop(Integer id,
            String name,
            String description, String address, String city,
            String phone,
            String email,
            Integer activeStatus,
            User user) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.activeStatus = activeStatus;
        this.user = user;
    }
    
    public Shop(Integer id,
            String name,
            String description, String address, String city,
            String phone,
            String email,
            Integer activeStatus,
            User user,
            Set<Category> categories
//            ,Set<Product> products
            ) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.activeStatus = activeStatus;
        this.user = user;
        this.categories = categories;
//        this.products = products;
    }


    // --- GETTERS/SETTERS ---
    

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    
    public List<ShopImage> getImages() {
		return images;
	}

	public void setImages(List<ShopImage> images) {
		this.images = images;
	}

	public String getUrlFb() {
		return urlFb;
	}

	public void setUrlFb(String urlFb) {
		this.urlFb = urlFb;
	}

	public String getUrlInsta() {
		return urlInsta;
	}

	public void setUrlInsta(String urlInsta) {
		this.urlInsta = urlInsta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	private static final long serialVersionUID = 274688743L;
    
    
}
