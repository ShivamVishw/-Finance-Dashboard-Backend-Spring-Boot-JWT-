package com.finance.dashboard.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.dashboard.dto.SummaryResponse;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.service.FinancialRecordService;
import com.finance.dashboard.service.FinancialRecordServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class FinancialRecordController {
	
	private final FinancialRecordServiceImpl recordService;
	
	@PostMapping
	public FinancialRecord add(@RequestBody FinancialRecord record) {
		return recordService.addRecord(record);
	}
	
	@GetMapping
	public List<FinancialRecord> getAll(){
		return recordService.getMyRecord();
	}
	
	@GetMapping("/type")
	public List<FinancialRecord> getByType(@RequestParam RecordType type){
		return recordService.getMyRecordByType(type);
	}
	
	@GetMapping("/summary")
	public SummaryResponse getSummary() {

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName();

	    return recordService.getSummary(email);
	}

}
