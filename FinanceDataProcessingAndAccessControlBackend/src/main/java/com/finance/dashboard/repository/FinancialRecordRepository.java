package com.finance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.entity.User;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long>{
	
	List<FinancialRecord> findByUser(User user);
	
	List<FinancialRecord> findByUserAndRecordType(User user, RecordType recordType);
	
}
