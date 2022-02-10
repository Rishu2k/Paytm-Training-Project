package com.spring.project12.services.interfaces;

import java.util.List;

import com.spring.project12.models.Employee;

public interface EmployeeServiceInterface {

	void addEmployee(Employee employee);

	List<Employee> getAllEmployees(Integer id);

	void deleteEmployee(Integer id);

	void updateEmployee(Employee employee);

}
