package com.delivery.deliveryrest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.delivery.deliveryrest.dto.RegisterDto;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) return true;
		
		RegisterDto registerDto = (RegisterDto) value;
		
		boolean isValid = registerDto.getPassword().equals(registerDto.getConfirmPassword());
		
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate("{com.delivery.deliveryrest.validation.ConfirmPassword.message}")
				.addPropertyNode("confirmPassword")
				.addConstraintViolation();
		}
		
		return isValid;
	}

}
