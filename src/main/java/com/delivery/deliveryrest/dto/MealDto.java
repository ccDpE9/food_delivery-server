package com.delivery.deliveryrest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDto {
	private String name;
	private String description;
	private double price;
	private RestaurantDto restaurant;
}
