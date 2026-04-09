package com.finance.dashboard.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.finance.dashboard.entity.Role;
import com.finance.dashboard.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
	
	
	private final RoleRepository roleRepository;
	
	public void run(String... args) {
		
		if(roleRepository.findByName("ADMIN").isEmpty()) {
			roleRepository.save(Role.builder().name("ADMIN").build());
		}
		
		if(roleRepository.findByName("ANALYST").isEmpty()) {
			roleRepository.save(Role.builder().name("ANALYST").build());
		}
		
		if(roleRepository.findByName("VIEWER").isEmpty()) {
			roleRepository.save(Role.builder().name("VIEWER").build());
		}
	}
	
}
