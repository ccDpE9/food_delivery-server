package com.delivery.deliveryrest.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			/* This if-else statement does not really addresses my point:
			 * Why is BasicAuthenticationFilter called on anonymous routes?
			 */
			/*
			 * Moreover, how come i 
			 */
			if (request.getCookies() == null) {
				filterChain.doFilter(request, response);
				return;
			}
			
			String token = Arrays.stream(request.getCookies())
					.filter(c -> c.getName().equals("jwt"))
					.findFirst()
					.map(Cookie::getValue)
					.orElse(null);
			
			if (token == null) {
				SecurityContextHolder.clearContext();
			}
			else {
				SecretKey key = Keys.hmacShaKeyFor("badkidspv95hoolsbadkidspv95hools".getBytes(StandardCharsets.UTF_8));

				Claims claims = Jwts
						.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(token)
						.getBody();
				List<String> roles = (List) claims.get("roles");
								
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
						claims.getSubject(),
						null,
						roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
				);
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				filterChain.doFilter(request, response);
			}
		}
		catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
	}

}
