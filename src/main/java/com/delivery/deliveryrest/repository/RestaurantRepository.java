package com.delivery.deliveryrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.deliveryrest.domain.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
	Restaurant findByName(String name);
	Restaurant findBySlug(String slug);
}