package com.spring.project12.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project12.models.PaymentInfo;
import com.spring.project12.services.PaymentService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	private static final Logger log = LogManager.getLogger(PaymentController.class);

	@PostMapping("/payment")
	@Transactional
	public void makePayment(@RequestBody PaymentInfo paymentInfo) {
		log.info("Processing Payment..");
		paymentService.makePayment(paymentInfo);
	}

}