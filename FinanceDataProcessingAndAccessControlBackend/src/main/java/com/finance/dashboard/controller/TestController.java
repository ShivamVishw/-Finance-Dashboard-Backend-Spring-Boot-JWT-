package com.finance.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	
	
	@GetMapping("/admin")
	public String admin() {
		return "ADMIN ACCESS";
	}
	
	@GetMapping("/analyst")
	public String analyst() {
		return "ANALYST ACCESS";
	}
	
	@GetMapping("/viewer")
	public String viewer() {
		return "VIEWER ACCESS";
	}
}
