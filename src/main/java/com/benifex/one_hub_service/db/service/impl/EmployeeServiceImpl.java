package com.benifex.one_hub_service.db.service.impl;

import com.benifex.one_hub_service.common.constants.MessageConstant;
import com.benifex.one_hub_service.common.exception.OneHubException;
import com.benifex.one_hub_service.db.entity.Employee;
import com.benifex.one_hub_service.db.repository.EmployeeRepository;
import com.benifex.one_hub_service.db.service.EmployeeService;
import com.benifex.one_hub_service.dto.request.EmployeeRequestDTO;
import com.benifex.one_hub_service.services.impl.BaseServiceImpl;
import com.benifex.one_hub_service.validation.groups.ValidationForRequest;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public Employee save(EmployeeRequestDTO employeeRequestDTO) throws OneHubException {
		try {
			validateRequest(employeeRequestDTO, EmployeeRequestDTO.class.getSimpleName(), ValidationForRequest.class);

			if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
				throw new OneHubException(MessageConstant.EMAIL_ALREADY_EXISTS);
			}

			Employee employee = new Employee()
					.setTitle(employeeRequestDTO.getTitle())
					.setFirstName(employeeRequestDTO.getFirstName())
					.setSurName(employeeRequestDTO.getSurName())
					.setDateOfBirth(LocalDateTime.parse(employeeRequestDTO.getDateOfBirth()))
					.setGender(employeeRequestDTO.getGender())
					.setEmail(employeeRequestDTO.getEmail())
					.setAddress(employeeRequestDTO.getAddress());

			return employeeRepository.saveAndFlush(employee);
		} catch (Exception e) {
			log.error("exception: ", e);

			if (MessageConstant.COMMON_VALIDATION_ERROR.equals(e.getMessage())) {
				throw e;
			}

			throw new OneHubException(e.getMessage());
		}
	}
}
