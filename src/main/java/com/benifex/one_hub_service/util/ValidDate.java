package com.benifex.one_hub_service.util;

import com.benifex.one_hub_service.common.constants.MessageConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateValidator.class)
public @interface ValidDate {

	String message() default MessageConstant.INVALID_DATE_FORMAT;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
