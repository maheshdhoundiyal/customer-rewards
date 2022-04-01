package com.customer.rewards.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.customer.rewards.constants.ApplicationConstants;
import com.customer.rewards.controller.CustomerRewardController;
import com.customer.rewards.entity.Customer;
import com.customer.rewards.model.CustomerResponse;
import com.customer.rewards.model.ResponseObject;
import com.customer.rewards.service.CustomerRewardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CustomerRewardController.class)
public class CustomerRewardControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerRewardService customerRewardService;

	@Test
	public void testGetCustomer() throws Exception {
		CustomerResponse customerResp = new CustomerResponse();
		customerResp.setCustomerName("Mahesh");
		Mockito.when(customerRewardService.getCustomerById(100)).thenReturn(new ResponseObject(
				ApplicationConstants.SUCCESS_STATUS_CODE, customerResp, ApplicationConstants.SUCCESS_MESSAGE));
		mockMvc.perform(get("/customers/100")).andExpect(status().isOk());
	}

	@Test
	public void testGetCustomerAll() throws Exception {
		List<Customer> customerList = new ArrayList<>();
		Customer customer = new Customer();
		customer.setName("Mahesh");
		customerList.add(customer);
		Mockito.when(customerRewardService.getCustomerAll()).thenReturn(new ResponseObject(
				ApplicationConstants.SUCCESS_STATUS_CODE, customerList, ApplicationConstants.SUCCESS_MESSAGE));
		mockMvc.perform(get("/customers")).andExpect(status().isOk());
	}

	@Test
	public void testPostCustomerAll() throws Exception {
		List<Customer> customerList = new ArrayList<>();
		Customer customer = new Customer();
		customer.setName("Mahesh");
		customerList.add(customer);
		Mockito.when(customerRewardService.getCustomerAll()).thenReturn(new ResponseObject(
				ApplicationConstants.SUCCESS_STATUS_CODE, customerList, ApplicationConstants.SUCCESS_MESSAGE));
		mockMvc.perform(post("/customers").content(asJsonString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	public void testCreateCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setName("Mahesh");
		Mockito.when(customerRewardService.saveCustomer(customer)).thenReturn(new ResponseObject(
				ApplicationConstants.SUCCESS_STATUS_CODE, customer, ApplicationConstants.SUCCESS_MESSAGE));
		mockMvc.perform(post("/createCustomers").content(asJsonString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
