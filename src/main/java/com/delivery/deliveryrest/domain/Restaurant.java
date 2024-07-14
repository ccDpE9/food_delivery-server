package com.delivery.deliveryrest.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = true)
	private Long id;

	@Column(unique = true)
	private String name;
	@Column(unique = true)
	private String slug;
	private String description;
	private double minimalDelivery;
	private int deliveryTime;
	private double deliveryPrice;
	private String category;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<Meal> meals = new ArrayList<>();

	@PrePersist
	private void slugify() {
		slug = name.toLowerCase().replace(" ", "_");
	}

	public void addMeal(Meal meal) {
		meals.add(meal);
		meal.setRestaurant(this);
	}

	public void removeMeal(Meal meal) {
		meals.remove(meal);
		meal.setRestaurant(null);
	}
}
