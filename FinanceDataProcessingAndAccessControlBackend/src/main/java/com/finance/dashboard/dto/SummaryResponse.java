package com.finance.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummaryResponse {
	private Double totalIncome;
	private Double totalExpense;
	private Double balance;
}
