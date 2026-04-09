package com.finance.dashboard.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	
	
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain)
				throws ServletException, IOException{
		
			String authHeader = request.getHeader("Authorization");
			
			if(authHeader == null || !authHeader.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			
			String token = authHeader.substring(7);
			String email = jwtUtil.extractEmail(token);
			
			var userDetails = userDetailsService.loadUserByUsername(email);
			
			if(jwtUtil.validateToken(token, userDetails.getUsername())) {
				var authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
						userDetails,
						null, 
						userDetails.getAuthorities()
						);
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
						
			}
			
			filterChain.doFilter(request, response);
	}
	
}
