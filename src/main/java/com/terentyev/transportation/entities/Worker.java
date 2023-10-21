package com.terentyev.transportation.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "workers")
public class Worker {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=3, message = "Не меньше 3 знаков")
    private String username;
    private String lastName;
    private String firstName;
    private String surName;
    //@Valid
    //@Email
    @Column(columnDefinition = "character varying (30)")
    private String email;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;
    @Transient
    private String passwordConfirm;
    @Column(columnDefinition = "character varying (40)")
    private String fullName;
    private Long licenseNumber = 0L;
    private Category category;
    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
            name = "workers_roles", 
            joinColumns = @JoinColumn(
              name = "worker_id", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(
              name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    */
    @Column(name = "is_boss", nullable = false, columnDefinition = "boolean default false")
    private boolean boss;

    public Worker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getUsername() {
        return username;
    }
    
    
    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


    public void setUsername(String username) {
        this.username = username;
    }

    
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if (boss) return Collections.singletonList(new SimpleGrantedAuthority("ROLE_BOSS"));
       return Collections.singletonList(new SimpleGrantedAuthority("ROLE_DRIVER"));// return getRoles();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

	

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public Long getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(Long licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private enum Category{
		A, A1, B1, BE, B, C1, CE, C, C1E, D, DE, D1, D1E, M, TM, TB;
	}

}
