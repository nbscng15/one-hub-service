package com.benifex.one_hub_service.dto.request;

import com.benifex.one_hub_service.common.constants.MessageConstant;
import com.benifex.one_hub_service.validation.groups.ValidationForRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeRequestDTO {

	@NotBlank(groups = {ValidationForRequest.class}, message = MessageConstant.FIELD_REQUIRED_MSG)
	private String title;

	@NotBlank(groups = {ValidationForRequest.class}, message = MessageConstant.FIELD_REQUIRED_MSG)
	private String firstName;

	@NotBlank(groups = {ValidationForRequest.class}, message = MessageConstant.FIELD_REQUIRED_MSG)
	private String surName;

	@NotNull(groups = {ValidationForRequest.class}, message = MessageConstant.FIELD_REQUIRED_MSG)
	private LocalDate dateOfBirth;

	@NotBlank(groups = {ValidationForRequest.class}, message = MessageConstant.FIELD_REQUIRED_MSG)
	private String gender;

	@NotBlank(groups = {ValidationForRequest.class}, message = MessageConstant.FIELD_REQUIRED_MSG)
	@Email(groups = {ValidationForRequest.class}, message = MessageConstant.INVALID_EMAIL_MSG)
	private String email;

	@NotBlank(groups = {ValidationForRequest.class}, message = MessageConstant.FIELD_REQUIRED_MSG)
	private String address;

}
