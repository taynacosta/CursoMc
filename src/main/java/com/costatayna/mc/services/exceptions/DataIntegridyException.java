package com.costatayna.mc.services.exceptions;

public class DataIntegridyException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegridyException(String msg) {
		super(msg);
	}
	
	public DataIntegridyException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
