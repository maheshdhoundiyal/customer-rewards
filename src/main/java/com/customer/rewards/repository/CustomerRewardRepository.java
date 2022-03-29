package com.customer.rewards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.rewards.entity.Customer;


@Repository
public interface CustomerRewardRepository extends JpaRepository<Customer, Integer>{

}
