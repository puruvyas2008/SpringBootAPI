package com.batch.springboot.accounts.dao;

import java.util.List;

import com.batch.springboot.accounts.model.Accounts;

public interface AccountsDAO {
	
	public List<Accounts> getAllAccounts();
	public  Accounts getAccount(int id);
	public Accounts createAccount(Accounts account);
	public Accounts updateAccount(Accounts account , int id);
	public Accounts deleteAccount(int id);
	

}
