/**
 * 
 */
package com.expense.dao.impl;

import java.util.List;

import com.expense.domains.Budget;
import com.expense.domains.Expense;
import com.expense.models.Report;

/**
 * @author jitender
 * 
 */
public interface ReportDAO {

	List<Expense> getExpense4Report(String userid, Report report,Integer year, Integer month);

	List<Budget> getBudgets4Report(String userid);

}
