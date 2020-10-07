package com.delivery.deliveryrest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealOverviewDto {
	private String name;
	private String slug;
	private String description;
	private double price;
	private String category;
}
