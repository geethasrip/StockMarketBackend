package com.stockmarket.exception;
public class JwtTokenMalformedException extends Exception {
	private static final long serialVersionUID = 1L;

	public JwtTokenMalformedException(String msg) {
		super(msg);
	}

}