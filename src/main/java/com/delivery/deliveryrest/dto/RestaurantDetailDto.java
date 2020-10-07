package com.delivery.deliveryrest.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDetailDto {
	private String name;
	private String slug;
	private String description;
	private double minimalDelivery;
	private int deliveryTime;
	private double deliveryPrice;
	private String category;
	private List<MealOverviewDto> meals;
}
