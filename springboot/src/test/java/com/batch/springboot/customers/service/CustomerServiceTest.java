package com.batch.springboot.customers.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.batch.springboot.accounts.model.Accounts;
import com.batch.springboot.accounts.service.repository.AccountRepository;
import com.batch.springboot.customers.model.Customer;
import com.batch.springboot.customers.repository.CustomerRepositories;
import com.batch.springboot.customers.service.CustomerService;




@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
	
	@InjectMocks
	CustomerService customerService;
	
	@Mock
	CustomerRepositories customerRepositories;
	@Mock
	AccountRepository accountRepositories;

	@Test
	@Order(1)
	void getAllCustomersTest() {	
		ArrayList<Customer> customers= new ArrayList<Customer>();
		Customer customer1 = new Customer("Amit","Kumar","Ab@gmail.com");
		Customer customer2 = new Customer("Ankit","choudhary","Abc@gmail.com");
		customers.add(customer1);
		customers.add(customer2);
		when(customerRepositories.findAll()).thenReturn(customers);
		customerService.getAllCustomers();
		verify(customerRepositories, atLeastOnce()).findAll();	
	}

	
	@Test
	public void getCustomerByIdTest()
	{
		Optional<Customer> customer = Optional.of(new Customer("Amit","kumat","a@b.com"));
		 when(customerRepositories.findById(1)).thenReturn(customer);
		 
		Customer cust= customerService.getCustomer(1);
		
		assertEquals("Amit", cust.getFirstName());
		
		
	}
	@Test
	public void getCustomerByIdTestFail()
	{
		Optional<Customer> customer = Optional.of(new Customer("Amit","kumat","a@b.com"));
		 when(customerRepositories.findById(1)).thenReturn(customer);
		 
		Customer cust= customerService.getCustomer(1);
		
		assertNotEquals("Amitk", cust.getFirstName());
		
		
	}
	
	@Test
	public void createAccountsForCustomerTest() {
		Accounts account= new Accounts("saving",100);
		when(accountRepositories.save(account)).thenReturn(account);
		
		Accounts accounts= accountRepositories.save(account);
		
		assertEquals(100, accounts.getBalance());
		
	}
	@Test
	public void createAccountsForCustomerTestFail() {
		Accounts account= new Accounts("saving",100);
		when(accountRepositories.save(account)).thenReturn(account);
		
		Accounts accounts= accountRepositories.save(account);
		
		assertNotEquals(200, accounts.getBalance());
		
	}
	
	@Test
	public void updateCustomerTest() {
		Customer customer1 = new Customer("Amit","Kumar","Ab@gmail.com");
		when(customerRepositories.save(customer1)).thenReturn(customer1);
		
		Customer customer= customerRepositories.save(customer1);
		
		assertEquals("Amit", customer.getFirstName());
		
	}
	@Test
	public void updateCustomerTestFail() {
		Customer customer1 = new Customer("Amit","Kumar","Ab@gmail.com");
		when(customerRepositories.save(customer1)).thenReturn(customer1);
		
		Customer customer= customerRepositories.save(customer1);
		
		assertNotEquals("AmitK", customer.getFirstName());
		
	}
}
