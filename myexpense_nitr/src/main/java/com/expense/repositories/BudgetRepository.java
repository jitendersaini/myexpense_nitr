/**
 * 
 */
package com.expense.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expense.domains.Budget;

/**
 * @author jitender
 * 
 */
public interface BudgetRepository extends MongoRepository<Budget, String> {

	Budget findById(String id);

	Budget findByUseridAndMonthAndYear(String userid, Integer month,
			Integer year);

	List<Budget> findByUserid(String userid);
}
