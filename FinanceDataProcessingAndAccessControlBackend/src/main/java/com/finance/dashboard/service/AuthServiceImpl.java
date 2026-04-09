package com.finance.dashboard.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finance.dashboard.dto.LoginRequest;
import com.finance.dashboard.dto.RegisterRequest;
import com.finance.dashboard.entity.Role;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.repository.RoleRepository;
import com.finance.dashboard.repository.UserRepository;
import com.finance.dashboard.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	
	@Override
	public String register(RegisterRequest request) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findByName(request.getRole())
				.orElseThrow(() -> new IllegalArgumentException("Invalid role"));
		
		User user = User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(role)
				.active(true)
				.build();
		userRepository.save(user);			
		
		return "User registered successfully";
	}

	@Override
	public String login(LoginRequest request) {
		// TODO Auto-generated method stub
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
						)
				);
		
		return jwtUtil.generateToken(request.getEmail());
	}
		
	
	
}
