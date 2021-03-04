package com.batch.springboot.customers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.batch.springboot.accounts.model.Accounts;
import com.batch.springboot.customers.common.NotFoundExecptions;
import com.batch.springboot.customers.model.Customer;
import com.batch.springboot.customers.service.CustomerService;

@RestController
@RequestMapping("/springboot")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping("/customers/accounts")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();

		if (allCustomers.isEmpty()) {
			throw new NotFoundExecptions("No customer found");
		}

		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/customers/{id}/accounts")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
		Customer customer = customerService.getCustomer(id);
		if (customer.getCustomerId() == 0) {
			throw new NotFoundExecptions("No customer found with given id");
		}
		return ResponseEntity.ok().body(customer);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer customer1 = customerService.createCustomer(customer);
		if (customer1.getCustomerId() == 0) {
			throw new NotFoundExecptions("Customer record not created");
		}
		return ResponseEntity.accepted().body(customer1);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/customers/{id}/accounts")
	public ResponseEntity<String> createAccountForCustomer(@RequestBody Accounts account, @PathVariable int id) {
		String msg = customerService.createAccountForCustomer(account, id);
		if (msg == null) {
			throw new NotFoundExecptions("Something went wrong");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable int id) {
		Customer customer1 = customerService.updateCustomer(customer, id);
		if (customer1.getCustomerId() == 0) {
			throw new NotFoundExecptions("No customer found with given id");
		}
		return ResponseEntity.accepted().body(customer1);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		String msg = customerService.deleteCustomer(id);
		if (msg == null) {
			throw new NotFoundExecptions("No customer found with given id");
		}
		return ResponseEntity.accepted().body(msg); 

	}

}
