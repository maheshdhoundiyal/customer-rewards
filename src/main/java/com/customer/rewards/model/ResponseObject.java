package com.customer.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {

	private String statusCode;
	private Object data;
	private String message;
	
}
