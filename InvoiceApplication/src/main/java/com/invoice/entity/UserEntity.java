package com.invoice.entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity

public class UserEntity {
	
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
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


	public Date getAccountCreated() {
		return accountCreated;
	}


	public void setAccountCreated(Date accountCreated) {
		this.accountCreated = accountCreated;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<InvoiceDetails> getInvoiceDetails() {
		return invoiceDetails;
	}


	public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	 @NotNull(message="field required")
	@Size(min = 5, message = "Name must have at least 5 characters")
	private String userName;
	 @NotNull(message="field required")
	@Email(message="enter the valid email")
	private String email;
	 
	@CreationTimestamp
	private Date accountCreated;
	@NotNull(message="field required")
	private String password;
	
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<InvoiceDetails> invoiceDetails;

	

}
