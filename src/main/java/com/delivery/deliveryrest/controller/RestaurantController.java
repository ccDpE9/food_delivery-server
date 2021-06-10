package com.delivery.deliveryrest.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryrest.domain.Restaurant;
import com.delivery.deliveryrest.dto.RestaurantDetailDto;
import com.delivery.deliveryrest.dto.RestaurantDto;
import com.delivery.deliveryrest.repository.MealRepository;
import com.delivery.deliveryrest.repository.OrderRepository;
import com.delivery.deliveryrest.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController()
public class RestaurantController {
	private final RestaurantRepository repository;
	private final ModelMapper modelMapper;

	@GetMapping("/api/restaurants")
	public List<RestaurantDto> get(
			@PageableDefault(page = 0, size = 10) @SortDefault.SortDefaults({
					@SortDefault(sort = "name") }) Pageable pageable,
			HttpServletResponse response) {
		List<Restaurant> restaurants = (List<Restaurant>) repository.findAll(pageable);
		response.setHeader("Access-Control-Expose-Headers", "content-range");
		response.setHeader("content-range", String.valueOf(restaurants.size()));
		return Arrays.asList(modelMapper.map(restaurants, RestaurantDto[].class));
	}

	@GetMapping("/api/restaurant")
	public RestaurantDetailDto get(@RequestParam("name") String slug) {
		Restaurant restaurant = repository.findBySlug(slug);
		return modelMapper.map(restaurant, RestaurantDetailDto.class);
	}

	@PostMapping("/api/restaurants")
	public void post(@RequestBody RestaurantDto dto) {
		Restaurant restaurant = modelMapper.map(dto, Restaurant.class);
		repository.save(restaurant);
	}
}