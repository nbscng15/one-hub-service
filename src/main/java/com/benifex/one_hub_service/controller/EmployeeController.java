package com.benifex.one_hub_service.controller;

import com.benifex.one_hub_service.common.constants.MessageConstant;
import com.benifex.one_hub_service.common.exception.OneHubException;
import com.benifex.one_hub_service.db.entity.Employee;
import com.benifex.one_hub_service.db.service.EmployeeService;
import com.benifex.one_hub_service.dto.request.EmployeeRequestDTO;
import com.benifex.one_hub_service.dto.response.EmployeeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Object> registerEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) throws OneHubException {

		Employee employee = employeeService.save(employeeRequestDTO);

		EmployeeResponseDTO response = new EmployeeResponseDTO();
		response.setSuccess(true);
		response.setMessage(MessageConstant.SUCCESSFUL_MSG);
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(employee);

		return ResponseEntity.ok(response);
	}

}
