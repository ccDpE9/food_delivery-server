package com.delivery.deliveryrest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.exceptionHandling(exceptions -> exceptions
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.OK)))
				.formLogin(form -> form
						.successHandler((req, res, auth) -> res.setStatus(201))
						.failureHandler(new SimpleUrlAuthenticationFailureHandler())
						.loginProcessingUrl("/authenticate")
						.usernameParameter("email"))
				.authorizeHttpRequests((authorize) -> authorize
						.anyRequest().permitAll());

		return http.build();
	}

}