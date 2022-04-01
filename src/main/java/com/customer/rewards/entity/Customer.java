package com.customer.rewards.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="customer")
public class Customer {
	public Customer(Integer id,String name) {
		this.id=id;
		this.name = name;
	}

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CustomerTransaction> transactions;	
	
}
