package com.spring.project12.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PaymentInfo {

	@NonNull
	private Integer departmentId;
	@NonNull
	private Integer employeeId;
	@NonNull
	private Double amount;
}