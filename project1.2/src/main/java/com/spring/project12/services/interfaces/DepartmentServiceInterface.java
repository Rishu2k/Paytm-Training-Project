package com.spring.project12.services.interfaces;

import java.util.List;

import com.spring.project12.models.Department;

public interface DepartmentServiceInterface {

	Department addDepartment(Department department);

	void deleteDepartment(Integer id);

	void updateDepartment(Department department, Integer id);

	List<Department> getAllDepartments();

}
