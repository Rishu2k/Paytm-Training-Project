package com.spring.project12.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project12.models.Employee;
import com.spring.project12.repositories.EmployeeRepository;
import com.spring.project12.services.interfaces.EmployeeServiceInterface;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees(Integer id) {
		return employeeRepository.findByDepartmentId(id);
	}

	@Override
	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public Boolean findIfEmployeeExists(Integer id, Integer departmentId) {
		try {
			return employeeRepository.findById(id).get().getDepartment().getId() == departmentId;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void updateAmount(Integer departmentId, Integer employeeId, double amount) {
		List<Employee> employees = getAllEmployees(departmentId);
		for (Employee employee : employees) {
			if (employee.getId() == employeeId) {
				employee.setAmount(employee.getAmount() + amount);
				updateEmployee(employee);
				return;
			}
		}
		throw new RuntimeException("Error Occured");
	}

}