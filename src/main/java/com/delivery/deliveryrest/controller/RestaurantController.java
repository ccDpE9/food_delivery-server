package com.delivery.deliveryrest.controller;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryrest.domain.Restaurant;
import com.delivery.deliveryrest.dto.RestaurantDetailDto;
import com.delivery.deliveryrest.dto.RestaurantDto;
import com.delivery.deliveryrest.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController()
public class RestaurantController {
	private final RestaurantRepository repository;
	private final ModelMapper modelMapper;
	
	@GetMapping("/api/restaurants")
	List<RestaurantDto> get() {
		List<Restaurant> restaurants = (List<Restaurant>) repository.findAll();
		return Arrays.asList(modelMapper.map(restaurants, RestaurantDto[].class));
	}
	
	@GetMapping("/api/restaurant")
	RestaurantDetailDto get(@RequestParam("name") String slug) {
		Restaurant restaurant = repository.findBySlug(slug);
		return modelMapper.map(restaurant, RestaurantDetailDto.class);
	}
	
	@PostMapping("/api/restaurants")
	void post(@RequestBody RestaurantDto dto) {
		Restaurant restaurant = modelMapper.map(dto, Restaurant.class);
		repository.save(restaurant);
	}
}
