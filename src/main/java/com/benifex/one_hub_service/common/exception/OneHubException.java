package com.benifex.one_hub_service.common.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class OneHubException extends Exception {

	private List<FieldError> errors = new ArrayList<>();

	public OneHubException(String message) {
		super(message);
	}

}
