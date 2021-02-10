package com.reg.ok.okstartup.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigInteger;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class UserJson {

	@NotBlank(message = "username cannot be null")
	private String username;
	
	@NotBlank(message = "password cannot be null")
	private String password;
	
	@NotBlank(message = "phone number cannot be null")
	private String phone;
	
	@NotNull(message = "salary cannot be null")
	@Min(value = 15000, message = "salary must more than 15000")
	private BigInteger salary;
	
	@NotBlank(message = "firstname cannot be null")
	private String firstname;
	
	@NotBlank(message = "firstname cannot be null")
	private String lastname;
	
	@NotBlank(message = "address cannot be null")
	private String address;
	
	@Email
	@NotBlank(message = "email cannot be null")
	private String email;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public BigInteger getSalary() {
		return salary;
	}
	public void setSalary(BigInteger salary) {
		this.salary = salary;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
