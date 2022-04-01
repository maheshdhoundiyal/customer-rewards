package com.customer.rewards.test;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.CustomerTransaction;
import com.customer.rewards.model.CustomerResponse;
import com.customer.rewards.model.ResponseObject;
import com.customer.rewards.repository.CustomerRewardRepository;
import com.customer.rewards.repository.CustomerTransactionRepository;
import com.customer.rewards.service.CustomerRewardService;

@ExtendWith(MockitoExtension.class)
public class CustomerRewardServiceTests {

	@InjectMocks
	private CustomerRewardService customerRewardService;

	@Mock
	private CustomerRewardRepository customerRepository;

	@Mock
	private CustomerTransactionRepository customerTransactionRepository;

	private Customer customer;
	private List<Customer> customerList = new ArrayList<Customer>();
	private Set<CustomerTransaction> transactions = new HashSet<CustomerTransaction>();

	@BeforeEach
	public void setup() {
		customer = new Customer(111, "TestUser");
		customerList.add(customer);
		transactions.add(new CustomerTransaction(1L, customer, 100.0, "Purchase 1", new Date()));
		transactions.add(new CustomerTransaction(2L, customer, 50.0, "Purchase 2", new Date()));
		transactions.add(new CustomerTransaction(3L, customer, 120.0, "Purchase 3", new Date()));
		customer.setTransactions(transactions);
	}

	@Test
	public void testCreateCustomer() throws Exception {

		doReturn(customer).when(customerRepository).save(customer);

		ResponseObject saveCustomer = customerRewardService.saveCustomer(customer);
		Customer data = (Customer) saveCustomer.getData();
		
		Assertions.assertEquals("TestUser", data.getName());

	}
	
	@Test
	public void testGetCustomerAll() throws Exception {

		doReturn(customerList).when(customerRepository).findAll();

		ResponseObject getCustomerAll = customerRewardService.getCustomerAll();
		@SuppressWarnings("unchecked")
		List<Customer> dataList = (List<Customer>) getCustomerAll.getData();
		
		Assertions.assertEquals(1, dataList.size());

	}
	
	@Test
	public void testGetCustomerById() throws Exception {

		doReturn(Optional.of(customer)).when(customerRepository).findById(111);

		ResponseObject demoCustomer1 = customerRewardService.getCustomerById(111);
		CustomerResponse data = (CustomerResponse) demoCustomer1.getData();

		Assertions.assertEquals(140L, data.getRewardPoints());

	}
	
	@Test
	public void testCalculateRewards() throws Exception {

		Long calculateRewardPoints = customerRewardService.calculateRewardPoints(transactions);

		// $100 purchase = 1x$50 = 50 points
		// $50 purchase = 1x$50 = 0 points
		// $120 purchase = 1x$50 + 2x$20 = 40 points

		Assertions.assertEquals(140L, calculateRewardPoints);

	}

}
