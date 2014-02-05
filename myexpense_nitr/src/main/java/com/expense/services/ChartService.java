/**
 * 
 */
package com.expense.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expense.domains.Budget;
import com.expense.domains.Expense;
import com.expense.utils.AppConstants;

/**
 * @author jitender
 * 
 */
@Service
@Transactional
public class ChartService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public Map<Integer, List<Expense>> getDataForYear(String userid,Integer year) {

		Map<Integer, List<Expense>> map = new HashMap<Integer, List<Expense>>();

		Calendar calendar1;
		Calendar calendar2;

		String[] arrMonth = AppConstants.MONTHS_ARRAY;
		Criteria c;

		for (int i = 0; i < arrMonth.length; i++) {
			calendar1 = new GregorianCalendar(
					year, i, Calendar.DATE);
			calendar1.set(Calendar.DATE,
					calendar1.getActualMinimum(Calendar.DATE));

			calendar2 = new GregorianCalendar(
					year, i, Calendar.DATE);
			calendar2.set(Calendar.DATE,
					calendar2.getActualMaximum(Calendar.DATE));
			c = new Criteria().andOperator(
					Criteria.where("expenseDate").gte(calendar1.getTime()),
					Criteria.where("expenseDate").lte(calendar2.getTime()));
			c = c.and("userid").in((Object[])userid.split(","));			
			Query query = new Query(c);
			List<Expense> l = mongoTemplate.find(query, Expense.class);
			if (null != l && !l.isEmpty()) {
				map.put(i, l);
			}
		}

		return map;

	}

	public Map<Integer, Double> getBudgetsForYear(String userid, Integer year) {		
		Query query = new Query(Criteria.where("userid").in((Object[])userid.split(",")).and("year")
				.is(year));
		List<Budget> list = mongoTemplate.find(query, Budget.class);
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for (Budget budget : list) {
			map.put(budget.getMonth(), budget.getValue());
		}
		return map;
	}
	
	public List<Integer> getBudgetsForUser(String userid) {		
		Query query = new Query(Criteria.where("userid").in((Object[])userid.split(",")));
		query.sort().on("year", Order.ASCENDING);
		List<Budget> list = mongoTemplate.find(query, Budget.class);
		Set<Integer> set = new TreeSet<Integer>();
		for (Budget budget : list) {
			set.add(budget.getYear());
		}
		
		return new ArrayList<Integer>(set);
		
	}

}
