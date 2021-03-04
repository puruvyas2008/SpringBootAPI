package com.batch.springboot.customers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch.springboot.accounts.model.Accounts;
import com.batch.springboot.accounts.service.repository.AccountRepository;
import com.batch.springboot.customers.common.NotFoundExecptions;
import com.batch.springboot.customers.model.Customer;
import com.batch.springboot.customers.repository.CustomerRepositories;

@Service
public class CustomerService {
	/*
	 * @Autowired CustomerDAO customerDAO;
	 */

	@Autowired
	CustomerRepositories customerRepository;

	@Autowired
	AccountRepository accountRepository;

	public List<Customer> getAllCustomers() {

		Iterable<Customer> customers = customerRepository.findAll();
		ArrayList<Customer> customers2 = new ArrayList<>();

		for (Customer c : customers) {
			customers2.add(c);
		}

		return customers2;
	}

	public Customer getCustomer(int id) {
		Optional<Customer> cust = customerRepository.findById(id);
		Customer customer;
		try {
			customer = cust.get();
		} catch (NoSuchElementException e) {

			throw new NotFoundExecptions("Customer Doesn't exists");
		}

		return customer;
	}

	public Customer createCustomer(Customer cust) {

		Customer customer = customerRepository.save(cust);
		return customer;
	}

	public String createAccountForCustomer(Accounts account, int id) {
		Optional<Customer> cust = customerRepository.findById(id);
		Customer customer;
		try {
			customer = cust.get();
		} catch (NoSuchElementException e) {

			throw new NotFoundExecptions("Customer Doesn't exists");
		}
		ArrayList<Accounts> accounts = new ArrayList<>();
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(customer);
		account.setCustomers(customers);
		accounts.add(account);
		customer.setAccounts(accounts);

		Accounts save = accountRepository.save(account);
		if (save.getAccountType() == null) {

			throw new NotFoundExecptions("Account was not created");
		}

		return "Account created successfully ";
	}

	public Customer updateCustomer(Customer customer, int id) {
		Customer cus = customer;
		cus.setCustomerId(id);
		Customer customer1 = customerRepository.save(cus);
		return customer1;

	}

	public String deleteCustomer(int id) {

		customerRepository.deleteById(id);
		return "deleted;";
	}

	

}
