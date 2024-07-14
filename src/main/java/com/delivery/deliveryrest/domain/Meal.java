package com.delivery.deliveryrest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(unique = true)
	private String slug;
	private String description;
	private double price;
	private String category;

	@ManyToOne(fetch = FetchType.LAZY)
	private Restaurant restaurant;

	@PrePersist
	private void slugify() {
		slug = restaurant.getSlug() + name.toLowerCase().replace(" ", "_");
	}
}
