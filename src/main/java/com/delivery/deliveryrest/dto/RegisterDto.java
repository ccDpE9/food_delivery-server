package com.delivery.deliveryrest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.delivery.deliveryrest.validation.ConfirmPassword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfirmPassword
public class RegisterDto {
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	@Email(message = "Email you've provided is not valid.")
	private String email;

	@NotBlank
	@Size(min = 8)
	private String password;
	
	@NotBlank
	@Size(min = 8)
	private String confirmPassword;
}
