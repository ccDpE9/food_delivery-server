package com.delivery.deliveryrest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryrest.domain.Meal;
import com.delivery.deliveryrest.domain.Restaurant;
import com.delivery.deliveryrest.dto.MealDto;
import com.delivery.deliveryrest.repository.MealRepository;
import com.delivery.deliveryrest.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class MealController {
	private final MealRepository mealRepository;
	private final RestaurantRepository restaurantRepository;
	private final ModelMapper modelMapper;
	
	@PostMapping("/api/meals")
	void post(@RequestBody MealDto dto) {
		Restaurant restaurant = restaurantRepository.findByName(dto.getRestaurant().getName());
		Meal meal = modelMapper.map(dto, Meal.class);
		meal.setRestaurant(restaurant);
		mealRepository.save(meal);
	}
}
