package com.finance.dashboard.service;

import java.util.List;

import com.finance.dashboard.dto.SummaryResponse;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.RecordType;

public interface FinancialRecordService {
	
	FinancialRecord addRecord(FinancialRecord record);
	
	List<FinancialRecord> getMyRecord();
	
	List<FinancialRecord> getMyRecordByType(RecordType type);
	
	
}
