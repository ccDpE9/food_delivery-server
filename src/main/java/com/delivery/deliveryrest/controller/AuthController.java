package com.delivery.deliveryrest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.delivery.deliveryrest.domain.User;
import com.delivery.deliveryrest.dto.RegisterDto;
import com.delivery.deliveryrest.repository.UserRepository;
import com.delivery.deliveryrest.validation.UserValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AuthController {
	private final UserRepository repository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserValidator userValidator;

	@InitBinder
	private void bindValidator(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(userValidator);
	}

	@PostMapping("/register")
	void register(@Valid @RequestBody RegisterDto dto) {
		User user = modelMapper.map(dto, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		repository.save(user);
	}
}