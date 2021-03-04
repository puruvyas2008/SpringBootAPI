package com.batch.springboot.accounts.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.batch.springboot.accounts.model.Accounts;

@Repository
public interface AccountRepository extends CrudRepository<Accounts, Integer> {

}
