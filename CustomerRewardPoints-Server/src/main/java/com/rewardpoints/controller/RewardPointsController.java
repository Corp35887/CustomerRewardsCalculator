package com.rewardpoints.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewardpoints.domain.Customer;
import com.rewardpoints.domain.Transaction;
import com.rewardpoints.dto.GetCustomerTransactionsResponse;
import com.rewardpoints.impl.RewardsCalculator;
import com.rewardpoints.repository.CustomerRepository;
import com.rewardpoints.repository.TransactionRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RewardPointsController {

	@Autowired
	RewardsCalculator rewardsCalculator;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * Will Retrieve all the customers from db
	 * 
	 * @return List of customers
	 */
	@GetMapping(path = "/rewardPoints/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * End point fetches transactions related to particular customer id for a
	 * month and calculate total reward points of the customer for the month
	 * 
	 * @param customerId
	 * @param year
	 * @param month
	 * @return
	 */
	@GetMapping(path = "/rewardPoints/customer/{customerId}/{year}/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GetCustomerTransactionsResponse getCustomerRewardPoints(@PathVariable Long customerId,
			@PathVariable Integer year, @PathVariable Integer month) {

		List<Transaction> transactions = transactionRepository.findAllByMonthAndYear(customerId, year, month);
		BigDecimal total = BigDecimal.ZERO;

		for (Transaction trans : transactions) {
			BigDecimal rewardPoints = rewardsCalculator.calculateRewardPoints(trans.getAmount());
			trans.setRewardPoints(rewardPoints);
			total = total.add(rewardPoints);
		}

		GetCustomerTransactionsResponse response = new GetCustomerTransactionsResponse();
		response.setTotalRewardPoints(total);
		response.setTransactions(transactions);
		return response;
	}
}
