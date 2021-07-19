package com.rewardpoints.dto;

import java.math.BigDecimal;
import java.util.List;

import com.rewardpoints.domain.Transaction;

public class GetCustomerTransactionsResponse {

	private List<Transaction> transactions;
	
	private BigDecimal totalRewardPoints;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public BigDecimal getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(BigDecimal totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}
	
	
}
