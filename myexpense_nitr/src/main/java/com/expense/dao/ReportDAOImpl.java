/**
 * 
 */
package com.expense.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expense.dao.impl.ReportDAO;
import com.expense.domains.Budget;
import com.expense.domains.Expense;
import com.expense.models.Report;

/**
 * @author jitender
 * 
 */
@Transactional
@Repository
public class ReportDAOImpl implements ReportDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Expense> getExpense4Report(String userid, Report report,Integer year, Integer month) {
		
					//System.out.println("report-----> "+report.getGrouping());
		        
		if (report.getFrom() == null && report.getTo() == null) {
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH,
	                calendar.getActualMinimum(Calendar.DAY_OF_MONTH)-1);			
			
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.DAY_OF_MONTH,
					calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));			
			
			/*Query query = new Query(Criteria.where("expenseDate.year").is(year).and("expenseDate.month").is(month).and("userid").is(userid));
			query.sort().on("categoryid", Order.ASCENDING);*/
			
			Criteria c = new Criteria().andOperator(Criteria.where("expenseDate").gte(calendar.getTime()),  
                    Criteria.where("expenseDate").lte(calendar1.getTime()));
			c = c.and("userid").in((Object[])userid.split(","));
			
			Query query = new Query(c);
			if(report.getGrouping()==1) {
				query.sort().on("categoryid", Order.ASCENDING).on("expenseDate", Order.DESCENDING);
			} else {
				query.sort().on("paidVia", Order.ASCENDING).on("expenseDate", Order.DESCENDING);
			}
	        // Execute the query and find all matching entries
			return mongoTemplate.find(query, Expense.class);
			
			/*return getSession()
					.createQuery(
							"from Expense where year(expenseDate) = year(current_date()) and month(expenseDate) = month(current_date()) and users.id=? order by year(expenseDate),month(expenseDate),category.id asc")
					.setParameter(0, userid).list();*/
		} else {
			/*return getSession()
					.createQuery(
							"from Expense where expenseDate > ? and expenseDate < ? and users.id=? order by year(expenseDate),month(expenseDate),category.id asc")
					.setParameter(0, report.getFrom())
					.setParameter(1, report.getTo()).setParameter(2, userid)
					.list();*/
			Criteria c = new Criteria().andOperator(Criteria.where("expenseDate").gte(report.getFrom()),  
                    Criteria.where("expenseDate").lte(report.getTo()));
			c = c.and("userid").in((Object[])userid.split(","));
			
			Query query = new Query(c);
			if(report.getGrouping()==1) {
				query.sort().on("categoryid", Order.ASCENDING).on("expenseDate", Order.DESCENDING);
			} else {
				query.sort().on("paidVia", Order.ASCENDING).on("expenseDate", Order.DESCENDING);
			}				
	        // Execute the query and find all matching entries
			return mongoTemplate.find(query, Expense.class);
		}

	}
	
	@Override
	public List<Budget> getBudgets4Report(String userid) {
		/*return getSession().createQuery("from Budget where uid=?")
				.setParameter(0, userid).list();*/
		
		Query query = new Query(Criteria.where("userid").in((Object[])userid.split(",")));
		
        // Execute the query and find all matching entries
		return mongoTemplate.find(query, Budget.class);
	}

}
