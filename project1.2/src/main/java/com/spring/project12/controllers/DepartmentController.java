package com.spring.project12.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project12.models.Department;
import com.spring.project12.services.DepartmentService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

	private static final Logger log = LogManager.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

//	@Autowired
//	private RestExceptionHandler restExceptionHandler;

//	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bad request")
//	@RequestMapping(method = RequestMethod.GET, value = "/error")
//	public void showErrorMsg() {
//		log.info("Error page accessed");
//	}

	@GetMapping(value="/",consumes = "*/*")
	public String showWelcomeMsg() {
		log.info("Project accessed");
		return "{\"response\":\"Welcome to Employee/Department Management System\"}";
	}

	@PostMapping("/department/{id}")
	public Department addDepartment(@RequestBody Department department) {
		log.info("Adding department");
		departmentService.addDepartment(department);
		return department;
	}

	@Cacheable(value = "departments")
	@GetMapping("/department")
	@ResponseBody
	public List<Department> getAllDepartments() {
		log.info("Getting all departments");
		return departmentService.getAllDepartments();
	}

	@CacheEvict(value = "departments", key = "#id")
	@DeleteMapping("/department/{id}")
	public String deleteDepartment(@PathVariable String id) {
		log.info("Deleting department");
		Integer deptId = Integer.valueOf(id);
		if (departmentService.findIfDepartmentExists(deptId)) {
			departmentService.deleteDepartment(deptId);
			return "{\"response\":\"Department Deleted\"}";
		} else {
			log.error("Department Not Found");
			return "{\"response\":\"Department Not Found\"}";
		}
	}

	@CachePut(value = "departments", key = "#id")
	@PutMapping("/department/{id}")
	public String updateDepartment(@RequestBody Department department, @PathVariable Integer id) {
		log.info("Updating department");
		if (departmentService.findIfDepartmentExists(id)) {
			departmentService.updateDepartment(department, id);
			return "{\"response\":\"Department Updated\"}";
		} else {
			log.error("Department Not Found");
			return "{\"response\":\"Department Not Found\"}";
		}
	}

	@CacheEvict(value = "departments", allEntries = true)
	@GetMapping("/department/clearCache")
	public void clearCache() {
		log.info("Cache for department cleared");
	}
}