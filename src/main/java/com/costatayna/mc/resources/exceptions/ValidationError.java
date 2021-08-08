package com.costatayna.mc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	private static final long serialVersionUID = 1L;

	public List<FildMessage> fildMessage = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FildMessage> getErrors() {
		return fildMessage;
	}

	public void addError(String fieldName, String message) {
		fildMessage.add(new FildMessage(fieldName, message));
	}

}
