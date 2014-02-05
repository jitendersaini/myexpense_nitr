/**
 * 
 */
package com.expense.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expense.domains.Category;

/**
 * @author jitender
 * 
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
	Category findById(String id);	
	List<Category> findByUserid(String userid);
}
