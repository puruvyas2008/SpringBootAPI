package com.batch.springboot.customers.common;

public class NotFoundExecptions extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundExecptions(String msg) {
		super(msg);
	}
	

}
