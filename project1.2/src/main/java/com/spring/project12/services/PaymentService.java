package com.spring.project12.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project12.models.PaymentInfo;
import com.spring.project12.services.interfaces.PaymentServiceInterface;

@Service
public class PaymentService implements PaymentServiceInterface {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;

	@Override
	public void makePayment(PaymentInfo paymentInfo) {
		departmentService.updateFund(paymentInfo.getDepartmentId(), paymentInfo.getAmount());
		employeeService.updateAmount(paymentInfo.getDepartmentId(), paymentInfo.getEmployeeId(),
				paymentInfo.getAmount());
	}

}