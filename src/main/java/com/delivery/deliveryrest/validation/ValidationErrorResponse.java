package com.delivery.deliveryrest.validation;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorResponse {
	private Map<String, String> violations = new HashMap<>();
}
