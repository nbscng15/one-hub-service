package com.benifex.one_hub_service.db.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(
	name = "employees",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_employee_email", columnNames = "email"),
		@UniqueConstraint(name = "uk_employee_no", columnNames = "employeeNo")
	}
)
public class Employee extends AuditTrail {

	@Column(name = "employee_no", updatable = false, nullable = false)
	@JdbcTypeCode(Types.VARCHAR)
	private final UUID employeeNo = UUID.randomUUID();

	@Column(name = "title")
	private String title;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "sur_name")
	private String surName;

	@Column(name = "date_of_birth")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

}
