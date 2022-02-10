package com.spring.project12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project12.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
