package com.costatayna.mc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	private static final long serialVersionUID = 1L;

	public List<FildMessage> fildMessage = new ArrayList<>();
	


	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FildMessage> getErrors() {
		return fildMessage;
	}

	public void addError(String fieldName, String message) {
		fildMessage.add(new FildMessage(fieldName, message));
	}

}
