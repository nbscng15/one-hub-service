package com.benifex.one_hub_service.db.service;

import com.benifex.one_hub_service.common.exception.OneHubException;
import com.benifex.one_hub_service.db.entity.Employee;
import com.benifex.one_hub_service.dto.request.EmployeeRequestDTO;

public interface EmployeeService {

	Employee save(EmployeeRequestDTO employeeRequestDTO) throws OneHubException;

}
