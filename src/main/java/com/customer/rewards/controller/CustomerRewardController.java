package com.customer.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.model.ResponseObject;
import com.customer.rewards.service.CustomerRewardService;

@RestController
public class CustomerRewardController {

	@Autowired
	private CustomerRewardService customerRewardService;

	@GetMapping("/customers")
	public ResponseEntity<ResponseObject> getCustomerAll() {
		return new ResponseEntity<ResponseObject>(customerRewardService.getCustomerAll(), HttpStatus.OK);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<ResponseObject> getCustomer(@PathVariable Integer id) {
		return new ResponseEntity<ResponseObject>(customerRewardService.getCustomerById(id), HttpStatus.OK);

	}
}
