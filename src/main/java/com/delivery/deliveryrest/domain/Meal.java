package com.delivery.deliveryrest.domain;

import javax.persistence.Column;
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
