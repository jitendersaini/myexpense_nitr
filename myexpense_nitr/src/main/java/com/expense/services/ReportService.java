/**
 * 
 */
package com.expense.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expense.dao.impl.ReportDAO;
import com.expense.domains.Budget;
import com.expense.domains.Expense;
import com.expense.models.Report;
import com.expense.models.ReportData;
import com.expense.utils.AppConstants;

/**
 * @author jitender
 * 
 */
@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportDAO reportDAO;

	public Map<Integer, Map<Integer, List<Expense>>> getExpense4Report(
			String userid, Report report) {

		Map<Integer, Map<Integer, List<Expense>>> mapMain = new HashMap<Integer, Map<Integer, List<Expense>>>();

		Calendar cal = Calendar.getInstance();

		List<Expense> list = reportDAO.getExpense4Report(userid, report,
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);

		Map<Integer, List<Expense>> map = new HashMap<Integer, List<Expense>>();

		for (Expense expense : list) {
			cal.setTime(expense.getExpenseDate());

			Integer year = cal.get(Calendar.YEAR);

			List<Expense> l;
			if (map.get(year) == null) {
				l = new ArrayList<Expense>();
			} else {
				l = map.get(year);
			}
			l.add(expense);

			map.put(year, l);
		}
		Set<Integer> set = map.keySet();

		for (Integer year : set) {
			List<Expense> list1 = map.get(year);
			Map<Integer, List<Expense>> map1 = new HashMap<Integer, List<Expense>>();

			for (Expense expense : list1) {

				cal.setTime(expense.getExpenseDate());

				Integer month = cal.get(Calendar.MONTH) + 1;

				List<Expense> l1;
				if (map1.get(month) == null) {
					l1 = new ArrayList<Expense>();
				} else {
					l1 = map1.get(month);
				}
				l1.add(expense);

				map1.put(month, l1);
			}

			mapMain.put(year, map1);

		}

		return mapMain;
	}

	public List<Budget> getBudgets4Report(String userid) {
		return reportDAO.getBudgets4Report(userid);
	}

	public List<ReportData> getAllExpense(List<Expense> list) {
		List<ReportData> list1 = new ArrayList<ReportData>();
		ReportData r;
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		for (Expense expense : list) {
			cal.setTime(expense.getExpenseDate());
			cal1.setTime(expense.getCreatedDate());
			cal2.setTime(expense.getModifiedDate());

			r = new ReportData();
			r.setYear(cal.get(Calendar.YEAR));
			r.setMonth(AppConstants.MONTHS_ARRAY[(cal.get(Calendar.MONTH))]);
			r.setTitle(expense.getExpenseTitle());
			r.setValue(expense.getExpenseValue());
			r.setCategory(expense.getCategory().getCategoryName());
			/*if (null != expense.getPaidVia()) {
				if (expense.getPaidVia() == 1) {
					r.setPaidVia("Cash");
				} else if (expense.getPaidVia() == 2) {
					r.setPaidVia("Credit Card");
				} else if (expense.getPaidVia() == 3) {
					r.setPaidVia("Debit Card");
				} else if (expense.getPaidVia() == 4) {
					r.setPaidVia("Cheque");
				} else if (expense.getPaidVia() == 5) {
					r.setPaidVia("Sodhexo Meal Pass");
				}
			}*/
			r.setPaidVia(expense.getPaidVia().getPaymentMode());
			r.setPaidViaType(AppConstants.PAYMENT_MODE_ARRAY[expense.getPaidVia().getPaymentModeType()]);
			r.setExpenseDateSort(expense.getExpenseDate());			
			r.setCreatedBy(expense.getUsers().getName());
			r.setModifiedBy(expense.getUsersModified().getName());
			r.setCreatedDateSort(expense.getCreatedDate());
			r.setModifiedDateSort(expense.getModifiedDate());

			r.setCreatedDate(AppConstants.MONTHS_ARRAY[(cal1
					.get(Calendar.MONTH))]
					+ " "
					+ cal1.get(Calendar.DAY_OF_MONTH)
					+ ", "
					+ cal1.get(Calendar.YEAR));
			r.setExpenseDate(AppConstants.MONTHS_ARRAY[(cal.get(Calendar.MONTH))]
					+ " "
					+ cal.get(Calendar.DAY_OF_MONTH)
					+ ", "
					+ cal.get(Calendar.YEAR));
			r.setModifiedDate(AppConstants.MONTHS_ARRAY[(cal2
					.get(Calendar.MONTH))]
					+ " "
					+ cal2.get(Calendar.DAY_OF_MONTH)
					+ ", "
					+ cal2.get(Calendar.YEAR));

			list1.add(r);
		}
		return list1;
	}

}
