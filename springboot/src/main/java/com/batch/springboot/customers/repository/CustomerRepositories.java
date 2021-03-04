package com.batch.springboot.customers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.batch.springboot.customers.model.Customer;


@Repository
public interface CustomerRepositories extends CrudRepository<Customer, Integer> {

}
