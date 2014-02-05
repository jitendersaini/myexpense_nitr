/**
 * 
 */
package com.expense.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expense.domains.PaymentMode;

/**
 * @author jitender
 * 
 */
public interface PayModeRepository extends MongoRepository<PaymentMode, String> {
	PaymentMode findById(String id);	
	List<PaymentMode> findByUserid(String userid);
}
