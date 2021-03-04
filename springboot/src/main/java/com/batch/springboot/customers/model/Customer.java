package com.batch.springboot.customers.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.batch.springboot.accounts.model.Accounts;

@Entity
@Table(name="customers")
public class Customer {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int customerId;
	private String firstName;
	private String	 LastName;
	private String	 email;
	
	@ManyToMany(mappedBy="customers",cascade=CascadeType.PERSIST)
	//@ManyToMany
	List<Accounts> accounts= new ArrayList<>();
	
	
	public Customer(int customerId, String firstName, String lastName, String email) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		LastName = lastName;
		this.email = email;
	}

	
	public Customer( String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.email = email;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the accounts
	 */
	public List<Accounts> getAccounts() {
		return accounts;
	}
	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", LastName=" + LastName + ", email="
				+ email + ", accounts=" + accounts + "]";
	}
	

}
