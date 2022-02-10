package com.spring.project12.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project12.models.Department;
import com.spring.project12.repositories.DepartmentRepository;
import com.spring.project12.services.interfaces.DepartmentServiceInterface;

@Service
public class DepartmentService implements DepartmentServiceInterface {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public void updateDepartment(Department department, Integer id) {
		departmentRepository.save(department);
	}

	public Boolean findIfDepartmentExists(Integer id) {
		return departmentRepository.findById(id).isPresent();
	}

	public void updateFund(Integer departmentId, double amount) {
		List<Department> departments = getAllDepartments();
		for (Department department : departments) {
			if (department.getId() == departmentId && department.getFund() >= amount) {
				department.setFund(department.getFund() - amount);
				updateDepartment(department, departmentId);
				return;
			}
		}
		throw new RuntimeException("Error Occured");
	}

}