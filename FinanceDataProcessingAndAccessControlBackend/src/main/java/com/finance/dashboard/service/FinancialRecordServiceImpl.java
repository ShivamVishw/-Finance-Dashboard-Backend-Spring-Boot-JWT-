package com.finance.dashboard.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.finance.dashboard.dto.SummaryResponse;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.repository.FinancialRecordRepository;
import com.finance.dashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinancialRecordServiceImpl implements FinancialRecordService{
	
	
	private final FinancialRecordRepository recordRepository;
	private final UserRepository userRepository;
	
	@Override
	public FinancialRecord addRecord(FinancialRecord record) {
		// TODO Auto-generated method stub
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userRepository.findByEmail(email).orElseThrow();
		
		record.setUser(user);
		
		return recordRepository.save(record);
	}

	@Override
	public List<FinancialRecord> getMyRecord() {
		// TODO Auto-generated method stub
		
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userRepository.findByEmail(email).orElseThrow();
		
		return recordRepository.findByUser(user);
	}

	@Override
	public List<FinancialRecord> getMyRecordByType(RecordType type) {
		// TODO Auto-generated method stub
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userRepository.findByEmail(email).orElseThrow();
		
		return recordRepository.findByUserAndRecordType(user, type);
	}
	
	public SummaryResponse getSummary(String email) {

	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    List<FinancialRecord> records = recordRepository.findByUser(user);

	    double income = records.stream()
	            .filter(r -> r.getRecordType().name().equals("INCOME"))
	            .mapToDouble(FinancialRecord::getAmount)
	            .sum();

	    double expense = records.stream()
	            .filter(r -> r.getRecordType().name().equals("EXPENSE"))
	            .mapToDouble(FinancialRecord::getAmount)
	            .sum();

	    return new SummaryResponse(
	            income,
	            expense,
	            income - expense
	    );
	}

}
