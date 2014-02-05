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

import com.expense.domains.Category;
import com.expense.domains.Users;
import com.expense.models.CategoryModal;
import com.expense.repositories.CategoryRepository;

/**
 * @author jitender
 * 
 */
@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	/*
	 * @Autowired private UsersService usersService;
	 */

	public void saveMultiple(CategoryModal categoryList, String loginId) {
		
		List<Category> mainList = new ArrayList<>();
		Category category;
		int i=0;
		Users users = new Users();
		users.setId(loginId);
		for (String name : categoryList.getCategoryName()) {
			category = new Category();
			category.setCategoryName(name);
			category.setCategoryType(categoryList.getCategoryType().get(i));
			category.setCreatedDate(new Date());
			category.setId(null);
			category.setModifiedDate(new Date());
			category.setUserid(loginId);
			category.setUsers(users);
			category.setUsersModified(users);
			mainList.add(category);
			i++;
		}
		// category.setUsers(usersService.findById());
		categoryRepository.save(mainList);
	}
	
	public void save(Category category) {
		if (StringUtils.isEmpty(category.getId())) {
			category.setCreatedDate(new Date());
		}
		category.setModifiedDate(new Date());
		// category.setUsers(usersService.findById());
		categoryRepository.save(category);
	}

	public void remove(Category category) {
		// categoryRepository.delete(category);
		Query query = new Query(Criteria.where("id").in(
				(Object[]) category.getId().split(",")));
		mongoTemplate.remove(query, Category.class);

	}

	public List<Category> getAllCategories(String userid, Short categoryType) {
		// return categoryRepository.findByUserid(userid);
		// System.out.println("userid from catagory: "+userid);
		Query query = new Query(Criteria.where("userid")
				.in((Object[]) userid.split(",")).and("categoryType")
				.is(categoryType));
		return mongoTemplate.find(query, Category.class);
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
