package com.benifex.one_hub_service.db.repository;

import com.benifex.one_hub_service.db.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	boolean existsByEmail(String email);

}
