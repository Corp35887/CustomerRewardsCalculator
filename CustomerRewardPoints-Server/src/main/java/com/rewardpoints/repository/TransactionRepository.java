package com.rewardpoints.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rewardpoints.domain.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	@Query("Select t from Transaction t where t.customerId = ?1 and year(t.postedTime) = ?2 and month(t.postedTime) = ?3")
	List<Transaction> findAllByMonthAndYear(Long customerId, Integer year, Integer month);
}
