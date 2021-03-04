package com.batch.springboot.accounts.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.batch.springboot.customers.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="accounts")
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNumber;
	private String accountType;
	private int balance;
	//@JsonIgnoreProperties("accounts")
	@JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
     @JoinTable(
			name = "customers_accounts", 
			joinColumns = @JoinColumn(name = "account_number"), 
			inverseJoinColumns = @JoinColumn(name = "customer_id")
			)
	private List<Customer> customers = new ArrayList<>();

	public Accounts() {
		super();
	}

	public Accounts(String accountType, int balance) {
		super();
		this.accountType = accountType;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Accounts [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ ", customers=" + customers + "]";
	}

}


