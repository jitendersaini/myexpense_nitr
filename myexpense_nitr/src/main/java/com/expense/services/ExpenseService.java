package com.expense.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expense.domains.Budget;
import com.expense.domains.Category;
import com.expense.domains.Expense;
import com.expense.domains.PaymentMode;
import com.expense.domains.Users;
import com.expense.models.ExpenseModal;
import com.expense.repositories.BudgetRepository;
import com.expense.repositories.CategoryRepository;
import com.expense.repositories.ExpenseRepository;

@Service
@Transactional
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public void saveBudget(Budget budget, String userid) {
		/*
		 * if (StringUtils.isEmpty(budget.getId())) {
		 * budgetRepository.save(budget); } else {
		 * budgetRepository.save(budget); }
		 */
		List<Budget> budgetList = getBudget(userid, budget.getMonth(),
				budget.getYear());
		if (!budgetList.isEmpty()) {
			Budget bud = budgetList.get(0);
			budget.setId(bud.getId());
		}
		budgetRepository.save(budget);
	}

	public List<Budget> getBudget(String userid, Integer month, Integer year) {
		/*
		 * return budgetRepository .findByUseridAndMonthAndYear(userid, month,
		 * year);
		 */
		Query query = new Query(Criteria.where("userid")
				.in((Object[]) userid.split(",")).and("month").is(month)
				.and("year").is(year));
		return mongoTemplate.find(query, Budget.class);
	}

	public List<Category> fetchCategories(String userid) {
		Query query = new Query(Criteria.where("userid")
				.in((Object[]) userid.split(",")).and("categoryType").is(1));
		query.sort().on("categoryName", Order.ASCENDING);
		return mongoTemplate.find(query, Category.class);
		// return categoryRepository.findByUserid(userid);
	}

	public void save(Expense expense) {
		if (StringUtils.isEmpty(expense.getId())) {
			expense.setCreatedDate(new Date());
		}
		expense.setModifiedDate(new Date());
		expenseRepository.save(expense);
	}

	public void saveMultiple(ExpenseModal expenseList, String loginId) {

		List<Expense> mainList = new ArrayList<>();
		Expense expense;
		int i = 0;
		Users users = new Users();
		users.setId(loginId);
		for (String name : expenseList.getExpenseTitle()) {
			expense = new Expense();
			expense.setCategory(new Category(expenseList.getCategoryid().get(i)));
			expense.setCategoryid(expenseList.getCategoryid().get(i));
			expense.setCreatedDate(new Date());
			expense.setExpenseDate(expenseList.getExpenseDate().get(i));
			expense.setExpenseTitle(name);
			expense.setExpenseValue(expenseList.getExpenseValue().get(i));
			expense.setId(null);
			expense.setModifiedDate(new Date());
			expense.setPaidViaId(expenseList.getPaidViaId().get(i));
			expense.setPaidVia(new PaymentMode(expenseList.getPaidViaId().get(i)));
			expense.setUserid(loginId);
			expense.setUsers(users);
			expense.setUsersModified(users);
			mainList.add(expense);
			i++;
		}
		// category.setUsers(usersService.findById());
		expenseRepository.save(mainList);
	}

	public List<Expense> getAllExpense(String uid, Date begin, Date end) {
		Criteria c = new Criteria().andOperator(Criteria.where("expenseDate")
				.gte(begin), Criteria.where("expenseDate").lte(end));
		c = c.and("userid").in((Object[]) uid.split(","));

		Query query = new Query(c);
		query.sort().on("expenseDate", Order.ASCENDING);
		List<Expense> l = mongoTemplate.find(query, Expense.class);
		return l;
	}

	public List<Category> fetchCategoriesEdit(String userid) {
		Query query = new Query(Criteria.where("userid").in(
				(Object[]) userid.split(",")));
		query.sort().on("categoryName", Order.ASCENDING);
		return mongoTemplate.find(query, Category.class);
	}

	public void remove(Expense expense) {
		// expenseRepository.delete(expense);

		Query query = new Query(Criteria.where("id").in(
				(Object[]) expense.getId().split(",")));
		mongoTemplate.remove(query, Expense.class);
	}

	public List<Budget> getMonYrs(String uid) {
		Query query = new Query(Criteria.where("userid").in(
				(Object[]) uid.split(",")));
		query.sort().on("year", Order.DESCENDING).on("month", Order.DESCENDING);
		return mongoTemplate.find(query, Budget.class);

		// return budgetRepository.findByUserid(uid);
	}

	public Expense isCategoryIdMapped(String id) {
		return expenseRepository.findByCategoryid(id);
	}
	
	public Expense isPaymentModeIdMapped(String id) {
		return expenseRepository.findBypaidViaId(id);
	}

	public List<Expense> isCategoryMapped(String id) {
		// return expenseRepository.findByCategoryid(id);
		Query query = new Query(Criteria.where("categoryid").in(
				(Object[]) id.split(",")));
		return mongoTemplate.find(query, Expense.class);
	}

	public Object fetchCategoriesNotification(String userid) {
		Query query = new Query(Criteria.where("userid")
				.in((Object[]) userid.split(",")).and("categoryType").is(2));
		query.sort().on("categoryName", Order.ASCENDING);
		return mongoTemplate.find(query, Category.class);
	}

}