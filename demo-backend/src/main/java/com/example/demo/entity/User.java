package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

import com.example.demo.utils.Constant;


@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = -2185803412812655677L;
	
    // --- ATTRIBUTES ---
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = Constant.EMAIL_REQUIRED)
    @Email(message= Constant.EMAIL_INVALID)
    @Column(nullable=false, unique=true)
	private String email;

	@NotEmpty(message = Constant.NAME_REQUIRED)
	@Size(min = 2, max = 24, message = Constant.USER_INCORRECT_SIZE)
	@Column(nullable=false)
	private String name;

    @NotEmpty(message = Constant.SURNAME1_REQUIRED)
    @Size(min = 2, max = 24, message = Constant.USER_INCORRECT_SIZE)
    @Column(nullable=false)
	private String surname1;

    @NotEmpty(message = Constant.SURNAME2_REQUIRED)
    @Size(min = 2, max = 24, message = Constant.USER_INCORRECT_SIZE)
    @Column(nullable=false)
	private String surname2;

    @NotEmpty(message = Constant.NAME_REQUIRED)
    @Size(min = 2, max = 24, message = Constant.USER_INCORRECT_SIZE)
	@Column(nullable=false, unique=true)
	private String login;

	@Formula("name || ' ' || surname1 || ' ' || surname2")
	private String fullName;

	@ManyToMany
	@JoinTable(name = "users_profiles_map", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "profile_id") })
	private Set<Profile> profiles = new HashSet<>();

    @NotEmpty(message = Constant.USER_PASSWORD_REQUIRED)
    @Size(min = 6, max = 24, message = Constant.USER_INCORRECT_SIZE)
    @Column(nullable=false)
	private String password;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
    
	@Column(name = "active_status")
	private Integer activeStatus;
	

    // ------ PREPERSIST ------
    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
    
    // ------ METHODS ------
    
	public User() {
		super();
	}

	public User(String email, String name, String surname1, String surname2, String login, Date createdAt, Integer activeStatus) {
		super();
		this.email = email;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.login = login;
		this.createdAt = createdAt;
		this.activeStatus = activeStatus;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<Profile> getProfiles() {
		return profiles;
	}

	public Set<Section> getSections() {
		Set<Section> sections = new HashSet<>();
		for (Profile profile : profiles) {
			sections.addAll(profile.getSections());
		}
		return sections;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}

	public void addProfile(Profile profile) {
		profiles.add(profile);
		profile.getUsers().add(this);
	}

	public void removeProfile(Profile profile) {
		profiles.remove(profile);
		profile.getUsers().remove(this);
	}

	public void addProfiles(Set<Profile> profiles) {
		this.profiles.addAll(profiles);
		profiles.forEach(profile -> profile.getUsers().add(this));
	}

	public void removeProfiles(Set<Profile> profiles) {
		this.profiles.removeAll(profiles);
		profiles.forEach(profile -> profile.getUsers().remove(this));
	}

	
	public static User from(String query, Date date, Integer status) {
		return new User(query, query, query, query, query, date, status);
	}

	/**
	 * Obtiene el nombre completo del usuario.
	 * 
	 * @return el nombre junto a sus apellidos.
	 */
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
