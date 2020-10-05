package com.delivery.deliveryrest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
	private String name;
	private String description;
	private double minimalDelivery;
	private int deliveryTime;
	private double deliveryPrice;
	private String category;

}
