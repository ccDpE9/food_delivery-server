package com.delivery.deliveryrest.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.delivery.deliveryrest.dto.RegisterDto;
import com.delivery.deliveryrest.repository.UserRepository;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegisterDto user = (RegisterDto) target;
		if (userRepository.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", null, "Email already exists.");
		}
	}
}
