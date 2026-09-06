package com.benifex.one_hub_service.services.impl;

import com.benifex.one_hub_service.common.constants.MessageConstant;
import com.benifex.one_hub_service.common.exception.OneHubException;
import com.benifex.one_hub_service.common.exception.OneHubExceptionBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@Slf4j
public class BaseServiceImpl {

	protected <T> void validateRequest(T object, String clazzName, Class<?> validationClass) throws OneHubException {
		OneHubExceptionBuilder builder = new OneHubExceptionBuilder();
		builder.setMessage(MessageConstant.COMMON_VALIDATION_ERROR);

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		SpringValidatorAdapter springValidatorAdapter = new SpringValidatorAdapter(validator);

		Set<ConstraintViolation<T>> fieldErrors = springValidatorAdapter.validate(object, validationClass);
		fieldErrors.forEach(error -> builder.fieldError(clazzName, error.getPropertyPath().toString(), error.getMessage()));

		if (!CollectionUtils.isEmpty(fieldErrors)) {
			throw builder.build();
		}
	}

}
