package com.votingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	 	
	 	@NotEmpty(message = "UserName is required")
	    private String username;
	   
	    @Email(message = "Invalid email format")
	    private String email;
	    
	    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must be 10 digits")
	    private String phoneNo;
	    
	    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long")
	    private String password;
	    
	    private String role;
		
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public User(Long id, @NotEmpty(message = "UserName is required") String username,
				@Email(message = "Invalid email format") String email,
				@Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits") String phoneNo,
				@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long") String password,
				String role) {
			super();
			this.id = id;
			this.username = username;
			this.email = email;
			this.phoneNo = phoneNo;
			this.password = password;
			this.role = role;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}	    
}
