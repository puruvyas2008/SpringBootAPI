package com.batch.springboot.accounts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntFunction;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch.springboot.accounts.model.Accounts;
import com.batch.springboot.accounts.model.TransferFund;
import com.batch.springboot.accounts.service.repository.AccountRepository;
import com.batch.springboot.customers.common.CommonStatus;
import com.batch.springboot.customers.common.NotFoundExecptions;

@Service
public class AccountsService {

	/*
	 * @Autowired AccountsDAO accountsDAO;
	 */

	@Autowired
	AccountRepository accountRepository;

	@Transactional
	public List<Accounts> getAllAccounts() {
		Iterable<Accounts> findAll = accountRepository.findAll();

		ArrayList<Accounts> accounts = new ArrayList<>();
		for (Accounts i : findAll) {
			accounts.add(i);
		}

		return accounts;
	}

	public Accounts getAccount(int id) {
		Optional<Accounts> findById = accountRepository.findById(id);
		Accounts accounts;
		try {

			accounts = findById.get();
		} catch (NoSuchElementException e) {

			throw new NotFoundExecptions("No accounts exists with given id");
		}
		return accounts;
	}

	@Transactional
	public CommonStatus transferFund(TransferFund fund) {
		Accounts accountFrom, accountTo;
		CommonStatus commonStatus = new CommonStatus();
		String msg = "Insufficient balance ";

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
			msg = "Transfer of funds sucessful";
		}

		commonStatus.setStatus(msg);

		return commonStatus;

	}

	Consumer<Accounts> c = account -> accountRepository.save(account);
	IntFunction<Optional<Accounts>> function = accountNumber -> accountRepository.findById(accountNumber);

	/*
	 * public Accounts createAccount(Accounts account) { return
	 * accountsDAO.createAccount(account); } public Accounts updateAccount(Accounts
	 * account , int id) { return accountsDAO.updateAccount(account, id); } public
	 * Accounts deleteAccount(int id) { return accountsDAO.deleteAccount(id); }
	 */

}
