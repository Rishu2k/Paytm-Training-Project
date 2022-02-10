package com.spring.project12.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project12.errorcontroller.RestExceptionHandler;
import com.spring.project12.models.Department;
import com.spring.project12.models.Employee;
import com.spring.project12.services.EmployeeService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	private static final Logger log = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RestExceptionHandler restExceptionHandler;

	@PostMapping("/department/{departmentId}/employee")
	public void addEmployee(@RequestBody Employee employee, @PathVariable Integer departmentId) {
		employee.setDepartment(new Department(departmentId, "", "", 0.0));
		employeeService.addEmployee(employee);
	}

	@GetMapping("/department/{departmentId}/employee")
	public List<Employee> getAllEmployees(@PathVariable Integer departmentId) {
		return employeeService.getAllEmployees(departmentId);
	}

	@DeleteMapping("/department/{departmentId}/employee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id, @PathVariable Integer departmentId) {
		if (employeeService.findIfEmployeeExists(id, departmentId)) {
			employeeService.deleteEmployee(id);
			return restExceptionHandler.successMsg();
		} else {
			log.error("Employee Not Found");
			return restExceptionHandler.entityNotFound();
		}
	}

	@PutMapping("/department/{departmentId}/employee/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable Integer departmentId,
			@PathVariable Integer id) {
		if (employeeService.findIfEmployeeExists(id, departmentId)) {
			employee.setDepartment(new Department(departmentId, "", "", 0.0));
			employeeService.updateEmployee(employee);
			return restExceptionHandler.successMsg();
		} else {
			log.error("Employee Not Found");
			return restExceptionHandler.entityNotFound();
		}
	}
}