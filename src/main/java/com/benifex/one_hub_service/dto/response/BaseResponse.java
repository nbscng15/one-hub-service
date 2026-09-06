package com.benifex.one_hub_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BaseResponse {

	// response for successful responses
	private boolean success;
	private String message;
	private int statusCode;

	// response for error responses
	private String errorCode;
	private String errorMessage;


}
