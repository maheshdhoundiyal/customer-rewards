package com.customer.rewards.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransaction extends Reward {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn
	private Customer customer;

	private Double total;

	private String itemDesc;

	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;

	@Override
	public Long getPoints() {
		this.points = 0l;

		// 1 reward point for every dollar spent over $50
		if (this.total > 50 && this.total <= 100) {
			this.points += (this.total.intValue() - 50) * 1;
		}

		// 1 point for every dollar spent over $50 & 2 points for every dollar spent over $100
		if (this.total > 100) {
			this.points += 50;
			this.points += (this.total.intValue() - 100) * 2;
		}

		return this.points;
	}

}
