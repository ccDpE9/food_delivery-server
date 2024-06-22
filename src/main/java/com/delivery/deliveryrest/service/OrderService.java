package com.delivery.deliveryrest.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import com.delivery.deliveryrest.domain.Order;
import com.delivery.deliveryrest.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;

	@PostAuthorize("returnObject.user.email == authentication.name")
	public Order readOrder(Long id) {
		return orderRepository.findById(id).orElseThrow();
	}

}
