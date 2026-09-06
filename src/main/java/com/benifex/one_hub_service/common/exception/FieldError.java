package com.benifex.one_hub_service.common.exception;

import lombok.Getter;

@Getter
public class FieldError extends OneHubException {

	private String object;
	private String field;

	public FieldError(String object, String field, String message) {
		super(message);
		this.object = object;
		this.field = field;
	}

}
