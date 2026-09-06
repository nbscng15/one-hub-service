package com.benifex.one_hub_service.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

	private static final DateTimeFormatter FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd").withResolverStyle(ResolverStyle.STRICT);

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null || value.isBlank()) {
			return true;
		}

		try {
			LocalDate.parse(value, FORMATTER);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
