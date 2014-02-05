/**
 * 
 */
package com.expense.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expense.domains.Users;

/**
 * @author jitender
 * 
 */
public interface UserRepository extends MongoRepository<Users, String> {

	Users findById(String id);
	Users findByEmail(String email);
	Users findByUsername(String username);	
	List<Users> findByJoinedid(String joinedid);	
	Users findByIdAndPassword(String id,String password);	
	List<Users> findByUsernameAndNameAndEmail(String username, String name,String email);
}
