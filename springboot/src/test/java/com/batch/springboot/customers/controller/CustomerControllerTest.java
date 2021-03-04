package com.batch.springboot.customers.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.batch.springboot.customers.controller.CustomerController;
import com.batch.springboot.customers.model.Customer;
import com.batch.springboot.customers.service.CustomerService;


@ExtendWith(SpringExtension.class) 
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {
		@Autowired
		private MockMvc mockMvc;
		@MockBean
		private CustomerService service;

		@Test
		@Order(1)
		void getAllCustomerTest() throws Exception {
					
			RequestBuilder request;
			
			List<Customer> asList = Arrays.asList(
					new Customer(1,"Amit","Kumar","ab@gmail.com"),
					new Customer(2,"Ankit","kumar","sj@nm.com")
					);
			
			System.out.println("Response :"+asList.toString());
			when(service.getAllCustomers()).thenReturn(
					asList);
			request=MockMvcRequestBuilders
					.get("/springboot/customers/accounts")
					.accept(MediaType.APPLICATION_JSON);
			
			String expectedResult="[{customerId:1,firstName:Amit,lastName:Kumar,email:ab@gmail.com},{customerId:2,firstName:Ankit,lastName:kumar,email:sj@nm.com}]";
			MvcResult result =mockMvc.perform(request)
				   .andExpect(status().isAccepted())
				   .andExpect(content().json(expectedResult))
				   .andReturn();
			
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
		
			
		}

}
