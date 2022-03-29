package com.customer.rewards.constants;

public final class ApplicationConstants {
	
	private ApplicationConstants() {

	}

	public static final String SUCCESS_STATUS_CODE = "200";
	public static final String FAILURE_STATUS_CODE = "300";
	public static final String FAILURE_METHOD_NOT_SUPPORTED_STATUS_CODE = "301";
	
	public static final String SUCCESS_MESSAGE = "success";
	public static final String FAILURE_MESSAGE_CUSTOMER_NOT_FOUND = "Customer not found for provided customer id";
	public static final String FAILURE_METHOD_NOT_SUPPORTED = "Method not supported, Change method to GET request";
	public static final String FAILURE_MESSAGE = "Please try after sometime,internal server error";

}
