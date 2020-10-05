package com.delivery.deliveryrest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	private String description;
	private double minimalDelivery;
	private int deliveryTime;
	private double deliveryPrice;
	private String category;
	
	@OneToMany(
		mappedBy = "restaurant",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Meal> meals = new ArrayList<>();
	
	public void addMeal(Meal meal) {
		meals.add(meal);
		meal.setRestaurant(this);
	}
	
	public void removeMeal(Meal meal) {
		meals.remove(meal);
		meal.setRestaurant(null);
	}
}
