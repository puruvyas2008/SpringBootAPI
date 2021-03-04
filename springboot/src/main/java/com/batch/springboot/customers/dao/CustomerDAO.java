package com.batch.springboot.customers.dao;

import java.util.List;

import com.batch.springboot.customers.model.Customer;

public interface CustomerDAO {

	public List<Customer> getAllCustomers();
	
	public Customer getCustomer(int id);
	
	public Customer createCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer, int id);
	
	public Customer deleteCustomer(int id);
}
