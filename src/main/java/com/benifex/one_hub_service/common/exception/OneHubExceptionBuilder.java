package com.benifex.one_hub_service.common.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

@Setter
public class OneHubExceptionBuilder {

	private String message;
	private List<FieldError> errors = new ArrayList<>();

	public OneHubExceptionBuilder fieldError(String object, String field, String message) {
		errors.add(new FieldError(object, field, message));
		return this;
	}

	public OneHubException build() {
		OneHubException oneHubException = new OneHubException(message);
		oneHubException.setErrors(errors);
		return oneHubException;
	}

}
