package com.batch.springboot.common;

public class NotFoundExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundExecption(String msg)
	{
		super(msg);
	}
	
}
