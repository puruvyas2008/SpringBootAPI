package com.batch.springboot.accounts.conroller;

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
import com.batch.springboot.accounts.model.TransferFund;
import com.batch.springboot.accounts.service.AccountsService;
import com.batch.springboot.customers.common.CommonStatus;
import com.batch.springboot.customers.common.NotFoundExecptions;
@RestController
@RequestMapping("/springboot")
public class AccountsController {
@Autowired
AccountsService accountService;
	
	
	
	@GetMapping("/accounts")
	public ResponseEntity<List<Accounts>> getAllAccounts(){
		  List<Accounts> accounts = accountService.getAllAccounts();
		  if (accounts.isEmpty()) {
			  throw new NotFoundExecptions("No accounts found");
		  }
		  return new ResponseEntity<List<Accounts>>(accounts,HttpStatus.ACCEPTED);
	}

	@RequestMapping(method=RequestMethod.GET,value="/accounts/{id}")
	public ResponseEntity<Accounts> getAccount(@PathVariable int id){
		
		Accounts accounts = accountService.getAccount(id);
		return ResponseEntity.status(HttpStatus.OK).body(accounts);
	} 
	@RequestMapping(method=RequestMethod.PUT,value="/accounts/transferFund")
	public ResponseEntity<CommonStatus> transferFund(@RequestBody TransferFund fund){
		
		CommonStatus msg= accountService.transferFund(fund);
		
		return ResponseEntity.accepted().body(msg);
		
		
	}

/*
	@RequestMapping(method=RequestMethod.POST,value="/accounts")
	public ResponseEntity<Accounts> createAccounts(@RequestBody Accounts account) {
		Accounts accounts = accountService.createAccount(account);
		return ResponseEntity.accepted().body(accounts);
	}


	@RequestMapping(method=RequestMethod.PUT,value="/accounts/{id}")
	public ResponseEntity<Accounts> updateAccounts(@RequestBody Accounts account ,@PathVariable int id) {
		Accounts accounts = accountService.updateAccount(account,id);
		return ResponseEntity.accepted().body(accounts);
	}

	@RequestMapping(method=RequestMethod.DELETE,value="/accounts/{id}")
	public ResponseEntity<Accounts> deleteAccounts(@PathVariable int id) {
		Accounts accounts = accountService.deleteAccount(id);
		return ResponseEntity.accepted().body(accounts);

	}*/
	}

