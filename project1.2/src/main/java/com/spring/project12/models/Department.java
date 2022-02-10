package com.spring.project12.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Department implements Serializable {

	@Id
	@NonNull
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String description;
	@NonNull
	private Double fund;
}