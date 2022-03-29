package com.customer.rewards.model;

import lombok.Data;

@Data
public class CustomerResponse {

	private Integer customerId;
	private String customerName;
	private Long rewardPoints;
}
