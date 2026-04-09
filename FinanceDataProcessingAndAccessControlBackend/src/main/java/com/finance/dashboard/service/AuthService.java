package com.finance.dashboard.service;

import com.finance.dashboard.dto.LoginRequest;
import com.finance.dashboard.dto.RegisterRequest;

public interface AuthService {
	
	String register(RegisterRequest request);
	
	String login(LoginRequest request);

}
