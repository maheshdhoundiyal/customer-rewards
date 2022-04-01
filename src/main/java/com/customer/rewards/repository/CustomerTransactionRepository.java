package com.customer.rewards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.rewards.entity.CustomerTransaction;


@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Integer>{

}
