package com.smart.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@NotBlank(message = "Name Field should not be empty..!!")
	private String userName;
	@Column(unique = true)
	@Pattern(regexp = "[A-Za-z0-9+_.-]+@(.+)$", message = "Please enter valid Email Id")
	private String email;
	@Size(min = 6, message = "Password Must be min 6 characters")
	private String password;
	private String userrole;
	private String userImage;
	@Column(length = 500)
	private String about;
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	@JsonManagedReference
	private List<Contact> contacts = new ArrayList<>();
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserrole() {
		return userrole;
	}


	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}


	public String getUserImage() {
		return userImage;
	}


	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", userrole=" + userrole + ", userImage=" + userImage + ", about=" + about + ", enabled=" + enabled
				+ ", contacts=" + contacts + "]";
	}


	
	
	
	
	
	
}
