package com.delivery.deliveryrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.deliveryrest.domain.Meal;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {}
