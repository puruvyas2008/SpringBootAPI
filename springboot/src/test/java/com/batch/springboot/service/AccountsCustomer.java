package com.batch.springboot.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch.springboot.accounts.model.Accounts;
import com.batch.springboot.accounts.service.repository.AccountRepository;
import com.batch.springboot.customers.model.Customer;
import com.batch.springboot.customers.repository.CustomerRepositories;
//@DataJpaTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
class AccountsCustomer {

	@Autowired
	CustomerRepositories customerRepositories;
	@Autowired
	AccountRepository accountRepositories;
	
	
	
	
	@Test
	@Sql("/schema.sql")
	void test() {
		Accounts accounts= new Accounts();
		Accounts accounts1= new Accounts();
		
	
		accounts.setAccountType("saving");
		accounts.setBalance(100);
		

		accounts1.setAccountType("current");
		accounts1.setBalance(300);
		
		
		System.out.println("hii");
		Customer customer =new Customer();
		
		customer.setFirstName("puru");
		customer.setLastName("vyas");
		customer.setEmail("jfbdsk@kjdbkjsa.com");
		
		customer.getAccounts().add(accounts);
		customer.getAccounts().add(accounts1);
		
		Customer customer1 =new Customer();
		
		customer1.setFirstName("Amit");
		customer1.setLastName("kumar");
		customer1.setEmail("jfbdsk@kjdbkjsa.com");
		
		customer1.getAccounts().add(accounts);
		customer1.getAccounts().add(accounts1);
		
		accounts.getCustomers().add(customer);
		accounts1.getCustomers().add(customer);
		
        accounts.getCustomers().add(customer1);
		accounts1.getCustomers().add(customer1);
		
		
		customerRepositories.save(customer);
		customerRepositories.save(customer1);
		System.out.println("hii");
		
		Iterable<Customer> customers = customerRepositories.findAll();
		ArrayList<Customer> customers2 = new ArrayList<>();

		for (Customer c : customers) {
			customers2.add(c);
			
		}
  System.out.println(customers2.get(0).getCustomerId());
	}
	
	@Test
	void saveAccounts() {
		Accounts accounts= new Accounts();
		Accounts accounts1= new Accounts();
		
		accounts.setAccountType("saving");
		accounts.setBalance(100);
		
		accounts1.setAccountType("current");
		accounts1.setBalance(100);
		
		
		
		Customer customer =new Customer();
		customer.setFirstName("puru");
		customer.setLastName("vyas");
		customer.setEmail("jfbdsk@kjdbkjsa.com");
		
		customer.getAccounts().add(accounts);
		customer.getAccounts().add(accounts1);
		
		Customer customer1 =new Customer();
		customer1.setFirstName("Amit");
		customer1.setLastName("kumar");
		customer1.setEmail("jfbdsk@kjdbkjsa.com");
		
		customer1.getAccounts().add(accounts);
		customer1.getAccounts().add(accounts1);
		
		accounts.getCustomers().add(customer);
		accounts1.getCustomers().add(customer);
		
        accounts.getCustomers().add(customer1);
		accounts1.getCustomers().add(customer1);
		
		
		accountRepositories.save(accounts1);
		accountRepositories.save(accounts);
		
	
		
	}

	
	@Test
	@Sql({"/schema.sql","/data.sql"})
	public void getAllCustomers() {
 
		Iterable<Customer> customers = customerRepositories.findAll();
		ArrayList<Customer> customers2 = new ArrayList<>();

		for (Customer c : customers) {
			customers2.add(c);
			
		}
  System.out.println(customers2.get(0).getCustomerId());
		
	}
	
}
