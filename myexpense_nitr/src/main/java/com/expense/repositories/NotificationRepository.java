/**
 * 
 */
package com.expense.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expense.domains.Notification;

/**
 * @author jitender
 * 
 */
public interface NotificationRepository extends MongoRepository<Notification, String> {

	Notification findById(String id);
	Notification findByCategoryid(String categoryId);	
	List<Notification> findByuserid(String userid);
}
