package com.benifex.one_hub_service.util;

import com.benifex.one_hub_service.common.constants.MessageConstant;
import com.benifex.one_hub_service.common.exception.FieldError;
import com.benifex.one_hub_service.common.exception.OneHubException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OneHubException.class)
	public ResponseEntity<Object> handleBizServiceException(OneHubException e, WebRequest request) {
		String exceptionMsg = e.getMessage();
		HttpStatus httpStatus = switch (exceptionMsg) {
			case MessageConstant.COMMON_VALIDATION_ERROR,
				 MessageConstant.INVALID_EMAIL_MSG,
				 MessageConstant.FIELD_REQUIRED_MSG,
				 MessageConstant.EMAIL_ALREADY_EXISTS -> HttpStatus.BAD_REQUEST;
			default -> HttpStatus.INTERNAL_SERVER_ERROR;
		};

		return ResponseEntity.status(httpStatus).body(generateFailResponse(httpStatus, e.getMessage(), e.getErrors()));
	}

	private Map<String, Object> generateFailResponse(HttpStatus httpStatus, String message, List<FieldError> errors) {
		Map<String, Object> response = createFailResponse(httpStatus, message);

		if (!CollectionUtils.isEmpty(errors)) {
			List<Map<String, String>> errorDetails = new ArrayList<>();
			for (FieldError error : errors) {
				log.info("object: {}, field: {}, message: {}", error.getObject(), error.getField(), error.getMessage());

				Map<String, String> errorDetail = new HashMap<>();
				errorDetail.put("field", error.getField());
				errorDetail.put("message", error.getMessage());

				errorDetails.add(errorDetail);
			}

			response.put("errorDetails", errorDetails);
		}

		return response;
	}

	private Map<String, Object> createFailResponse(HttpStatus httpStatus, String message) {
		Map<String, Object> jsonResponse = new LinkedHashMap<>();

		jsonResponse.put("status", httpStatus.name());
		jsonResponse.put("statusCode", httpStatus.value());
		jsonResponse.put("timestamp", LocalDateTime.now().toString());
		jsonResponse.put("message", message);

		return jsonResponse;
	}

}
