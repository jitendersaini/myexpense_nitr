/**
 * 
 */
package com.expense.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expense.domains.Expense;

/**
 * @author jitender
 * 
 */
public interface ExpenseRepository extends MongoRepository<Expense, String> {

	Expense findById(String id);
	Expense findByCategoryid(String categoryId);
	Expense findBypaidViaId(String paidViaId);
	List<Expense> findByuserid(String userid);
}
