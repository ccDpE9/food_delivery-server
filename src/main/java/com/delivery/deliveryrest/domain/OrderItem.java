package com.delivery.deliveryrest.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Meal meal;

	private int quantity;

	private String requirements;

	private double total;

	@PrePersist
	private void calculateTotal() {
		total = meal.getPrice() * quantity;
	}
}