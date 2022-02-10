package com.spring.project12.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

	@Id
	@NonNull
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String description;
	@NonNull
	private Double amount;

	@ManyToOne
	private Department department;

}