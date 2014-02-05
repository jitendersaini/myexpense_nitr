/**
 * 
 */
package com.expense.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expense.domains.Expense;
import com.expense.domains.PaymentMode;
import com.expense.domains.Users;
import com.expense.models.PaymentModeModal;
import com.expense.repositories.PayModeRepository;

/**
 * @author jitender
 * 
 */
@Service
@Transactional
public class PayModeService {

	@Autowired
	private PayModeRepository payModeRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public void saveMultiple(PaymentModeModal payModeList, String loginId) {

		List<PaymentMode> mainList = new ArrayList<>();
		PaymentMode paymentMode;
		int i = 0;
		Users users = new Users();
		users.setId(loginId);
		for (String name : payModeList.getPaymentMode()) {
			paymentMode = new PaymentMode();
			paymentMode.setPaymentMode(name);
			paymentMode.setPaymentModeType(payModeList.getPaymentModeType()
					.get(i));
			paymentMode.setCreatedDate(new Date());
			paymentMode.setId(null);
			paymentMode.setModifiedDate(new Date());
			paymentMode.setUserid(loginId);
			paymentMode.setUsers(users);
			paymentMode.setUsersModified(users);
			mainList.add(paymentMode);
			i++;
		}
		// category.setUsers(usersService.findById());
		payModeRepository.save(mainList);
	}

	public void save(PaymentMode paymentMode) {
		if (StringUtils.isEmpty(paymentMode.getId())) {
			paymentMode.setCreatedDate(new Date());
		}
		paymentMode.setModifiedDate(new Date());
		payModeRepository.save(paymentMode);
	}

	public void remove(PaymentMode paymode) {
		// categoryRepository.delete(category);
		Query query = new Query(Criteria.where("id").in(
				(Object[]) paymode.getId().split(",")));
		mongoTemplate.remove(query, PaymentMode.class);

	}

	public List<Expense> isPaymentModeMapped(String id) {
		// return expenseRepository.findByCategoryid(id);
		Query query = new Query(Criteria.where("paidVia").in(
				(Object[]) id.split(",")));
		return mongoTemplate.find(query, Expense.class);
	}

	public List<PaymentMode> getAllPaymentModes(String userid) {
		Query query = new Query(Criteria.where("userid").in(
				(Object[]) userid.split(",")));
		return mongoTemplate.find(query, PaymentMode.class);
	}
	
	public List<PaymentMode> getAllPaymentModesValues(String userid) {
		Query query = new Query(Criteria.where("userid").in(
				(Object[]) userid.split(",")));		
		return mongoTemplate.find(query, PaymentMode.class);
	}
	/*
	 * public List<Category> getAllCategoriesByUserName(String userid) {
	 * 
	 * //Query query = new Query(Criteria.where("users.@ref").is("users"));
	 * Query query =new Query(); // Execute the query and find all matching
	 * entries return mongoTemplate.find(query, Category.class);
	 * 
	 * //return categoryRepository.findByUsersAndCategoryName(new
	 * Users(userid),"Petrol"); }
	 */

}
