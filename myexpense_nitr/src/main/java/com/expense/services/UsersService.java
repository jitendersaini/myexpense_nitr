/**
 * 
 */
package com.expense.services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expense.domains.Users;
import com.expense.models.ChangePassword;
import com.expense.repositories.UserRepository;
import com.expense.utils.AppUtils;

/**
 * @author jitender
 * 
 */
@Service
@Transactional
public class UsersService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Users> readAll() {
		return userRepository.findByUsernameAndNameAndEmail("saini.charu",
				"Charu Saini", "saini.charu@gmail.com");
	}

	public String save(Users users) {
		//System.out.println("users.getjoinedid:::: "+users.getJoinedid());
		if (StringUtils.isNotEmpty(users.getId())) {
			Users userstmp = getUserForEdit(users.getId());
			userstmp.setColorTheme(users.getColorTheme());
			userstmp.setName(users.getName());
			userstmp.setEmail(users.getEmail());
			userstmp.setModifiedDate(new Date());			
			users = userstmp;
		} else {
			if (userRepository.findByUsername(users.getUsername()) != null) {
				return "username";
			} else if (userRepository.findByEmail(users.getEmail()) != null) {
				return "email";
			}
			Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			users.setPassword(passwordEncoder.encodePassword(
					users.getPassword(), null));
			users.setCreatedDate(new Date());
			users.setModifiedDate(new Date());
		}

		userRepository.save(users);
		return "save";
	}

	public Integer savePasswordChanged(ChangePassword cp) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		cp.setPassword(passwordEncoder.encodePassword(cp.getPassword(), null));
		cp.setNewpassword(passwordEncoder.encodePassword(cp.getNewpassword(),
				null));
		Query query = new Query(Criteria.where("id").is(cp.getUserid())
				.and("password").is(cp.getPassword()));
		Users usr = mongoTemplate.findOne(query, Users.class);
		if (null != usr) {
			usr.setPassword(cp.getNewpassword());
			usr.setModifiedDate(new Date());
			userRepository.save(usr);
			return 1;
		} else {
			return 0;
		}
	}

	public void remove(Users users) {
		userRepository.delete(users);
	}

	public Users getUserForEdit(String id) {
		return userRepository.findById(id);
	}

	public int resetAndSavePassword(String email) {
		Users user = userRepository.findByEmail(email);
		if (null == user) {
			return 0;
		} else {

			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100000);

			Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			user.setPassword(passwordEncoder.encodePassword(randomInt + "",
					null));

			userRepository.save(user);
			return randomInt;
		}
	}

	public Users findAccountById(String useridEmail, String joinPassword) {
		joinPassword = AppUtils.encodePasswordToMD5(joinPassword);
		Query query = new Query(Criteria.where("username").is(useridEmail)
				.and("password").is(joinPassword));
		Users user = mongoTemplate.findOne(query, Users.class);
		if (null == user) {
			query = new Query(Criteria.where("email").is(useridEmail)
					.and("password").is(joinPassword));
			user = mongoTemplate.findOne(query, Users.class);
		}
		return user;
	}
}
