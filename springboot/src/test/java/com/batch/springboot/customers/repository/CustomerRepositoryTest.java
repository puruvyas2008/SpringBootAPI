package com.batch.springboot.customers.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch.springboot.accounts.model.Accounts;
import com.batch.springboot.accounts.service.repository.AccountRepository;
import com.batch.springboot.customers.model.Customer;
import com.batch.springboot.customers.repository.CustomerRepositories;


@DataJpaTest
	
@ExtendWith(SpringExtension.class)
	
class CustomerRepositoryTest {

	

		@Autowired
		CustomerRepositories customerRepositories;
		@Autowired
		AccountRepository accountRepositories;
		
		
	
	@Test
	@Sql({"/schema.sql","/data.sql"})
	public void getAllCustomersTest() {
 
		Iterable<Customer> customers = customerRepositories.findAll();
		ArrayList<Customer> customers2 = new ArrayList<>();

		for (Customer c : customers) {
			customers2.add(c);
			
		}
  System.out.println(customers2.get(0).getCustomerId());
		
	}
	
	@Test
	@Sql({"/schema.sql","/data.sql"})
	public void getCustomer(int id) {
		Optional<Customer> cust = customerRepositories.findById(id);
		Customer customer=cust.get();
			System.out.println("Suctomer first name"+customer.getFirstName()); 
	}
	
	@Test
	@Sql({"/schema.sql","/data.sql"})
	public void createAccountForCustomerTest() {
		Optional<Customer> cust = customerRepositories.findById(1);
		Customer customer = cust.get();
		Accounts account= new Accounts();
		account.setAccountType("saving");
		account.setBalance(100);
		ArrayList<Accounts> accounts = new ArrayList<>();
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(customer);
		account.setCustomers(customers);
		accounts.add(account);
		customer.setAccounts(accounts);
	     accountRepositories.save(account);
	
	}
   @Test
   @Sql({"/schema.sql","/data.sql"})
   public void updateCustomer() {
		Customer cus = new Customer();
		cus.setCustomerId(1);
		cus.setFirstName("AmitK");
		cus.setLastName("choudhary");
		cus.setEmail("abh@gmail.com");
		customerRepositories.save(cus);
	}

}
