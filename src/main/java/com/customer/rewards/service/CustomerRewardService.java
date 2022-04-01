package com.customer.rewards.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.rewards.constants.ApplicationConstants;
import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.CustomerTransaction;
import com.customer.rewards.model.CustomerResponse;
import com.customer.rewards.model.ResponseObject;
import com.customer.rewards.repository.CustomerRewardRepository;

@Service
public class CustomerRewardService {

	@Autowired
	private CustomerRewardRepository customerRepository;

	public ResponseObject saveCustomer(Customer customer) {
		Customer save = customerRepository.save(customer);
		return new ResponseObject(ApplicationConstants.SUCCESS_STATUS_CODE, save,
				ApplicationConstants.SUCCESS_MESSAGE);
	}

	public ResponseObject getCustomerAll() {
		List<Customer> customer = customerRepository.findAll();
		if (customer.isEmpty()) {
			return new ResponseObject(ApplicationConstants.FAILURE_STATUS_CODE, customer,
					ApplicationConstants.FAILURE_MESSAGE_CUSTOMER_NOT_FOUND);
		} else {
			return new ResponseObject(ApplicationConstants.SUCCESS_STATUS_CODE, customer,
					ApplicationConstants.SUCCESS_MESSAGE);
		}
	}

	public ResponseObject getCustomerById(Integer customerId) {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			return new ResponseObject(ApplicationConstants.FAILURE_STATUS_CODE, customer,
					ApplicationConstants.FAILURE_MESSAGE_CUSTOMER_NOT_FOUND);
		} else {
			CustomerResponse customerResp = new CustomerResponse();
			customerResp.setCustomerId(customer.getId());
			customerResp.setCustomerName(customer.getName());
			if (customer.getTransactions() == null || customer.getTransactions().isEmpty()) {
				customerResp.setRewardPoints(0l);
			} else {
				customerResp.setRewardPoints(calculateRewardPoints(customer.getTransactions()));
			}
			return new ResponseObject(ApplicationConstants.SUCCESS_STATUS_CODE, customerResp,
					ApplicationConstants.SUCCESS_MESSAGE);
		}
	}
	
	public Long calculateRewardPoints(Set<CustomerTransaction> customerTransactions) {
		return customerTransactions.stream().map(txn -> txn.getPoints().intValue())
		.reduce(0, (a, b) -> a + b).longValue();
	}
}
