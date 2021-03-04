package com.batch.springboot.accounts.repo;


import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntFunction;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch.springboot.accounts.model.Accounts;
import com.batch.springboot.accounts.model.TransferFund;
import com.batch.springboot.accounts.service.repository.AccountRepository;
import com.batch.springboot.customers.common.NotFoundExecptions;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountsReposistry {
	@Autowired
	AccountRepository accountRepository;

	@Test
	@Transactional
	@Sql({ "/schema.sql", "/data.sql" })
	public void transferFundTes() {

		TransferFund fund = new TransferFund(1, 2, 10);
		Accounts accountFrom, accountTo;

		try {
			Optional<Accounts> account = function.apply(fund.getFromAccount());

			accountFrom = account.get();
		}
     
		catch (NoSuchElementException e) {

			throw new NotFoundExecptions("From account not found");
		}

		try {

			Optional<Accounts> account = function.apply(fund.getToAccount());
			accountTo = account.get();

		} catch (NoSuchElementException e) {

			throw new NotFoundExecptions("To account not found");
		}

		if (accountFrom.getBalance() >= fund.getAmount()) {
			accountFrom.setBalance(accountFrom.getBalance() - fund.getAmount());
			c.accept(accountFrom);
			accountTo.setBalance(accountTo.getBalance() + fund.getAmount());
			c.accept(accountTo);
		}

	}

	Consumer<Accounts> c = account -> accountRepository.save(account);
	IntFunction<Optional<Accounts>> function = accountNumber -> accountRepository.findById(accountNumber);

}
