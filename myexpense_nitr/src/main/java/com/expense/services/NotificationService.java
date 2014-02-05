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
import com.expense.domains.Notification;
import com.expense.domains.Users;
import com.expense.models.NotificationModal;
import com.expense.repositories.CategoryRepository;
import com.expense.repositories.NotificationRepository;
import com.expense.utils.AppUtils;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MailService mailService;

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

	public void save(Notification notification) {
		if (StringUtils.isEmpty(notification.getId())) {
			notification.setStatus((short) 1);
			notification.setCreatedDate(new Date());
		}
		notification.setModifiedDate(new Date());
		notificationRepository.save(notification);
	}

	public void saveMultiple(NotificationModal notificationList, String loginId) {
		List<Notification> mainList = new ArrayList<>();
		Notification notification;
		int i = 0;
		Users users = new Users();
		users.setId(loginId);
		for (String notTitle : notificationList.getNotifyTitle()) {
			notification = new Notification();
			notification.setCategory(new Category(notificationList
					.getCategoryid().get(i)));
			notification.setCategoryid(notificationList.getCategoryid().get(i));
			notification.setCreatedDate(new Date());
			notification.setId(null);
			notification.setModifiedDate(new Date());
			notification.setNotifyDays(notificationList.getNotifyDays().get(i));
			notification.setNotifyDueDate(AppUtils.getReducedDaysFromGivenDate(
					notificationList.getNotifyEndDate().get(i),
					notificationList.getNotifyDays().get(i)));
			notification.setNotifyEndDate(notificationList.getNotifyEndDate()
					.get(i));
			notification.setNotifyStartDate(notificationList
					.getNotifyStartDate().get(i));
			notification.setNotifyTitle(notTitle);
			notification.setNotifyVia(notificationList.getNotifyVia().get(i));
			notification.setStatus((short) 1);
			notification.setUserid(loginId);
			notification.setUsers(users);
			notification.setUsersModified(users);
			mainList.add(notification);
			i++;
		}		
		notificationRepository.save(mainList);
	}

	public List<Notification> getAllNotification(String uid) {

		// return notificationRepository.findByuserid(uid);

		Query query = new Query(Criteria.where("userid").in(
				(Object[]) uid.split(",")));
		return mongoTemplate.find(query, Notification.class);

	}

	public void remove(Notification notification) {
		// notificationRepository.delete(notification);
		Query query = new Query(Criteria.where("id").in(
				(Object[]) notification.getId().split(",")));
		mongoTemplate.remove(query, Notification.class);
	}

	public Notification isCategoryMapped(String id) {
		return notificationRepository.findByCategoryid(id);
	}

	public Object fetchCategoriesNotification(String userid) {
		Query query = new Query(Criteria.where("userid")
				.in((Object[]) userid.split(",")).and("categoryType").is(2));
		query.sort().on("categoryName", Order.ASCENDING);
		return mongoTemplate.find(query, Category.class);
	}

	public List<Notification> fetchEligibleNotifications() {
		Query query = new Query(Criteria.where("status").is(1)
				.and("notifyDueDate").lte(new Date()).and("notifyEndDate")
				.gte(new Date()));
		query.sort().on("notifyEndDate", Order.ASCENDING);
		List<Notification> list = mongoTemplate.find(query, Notification.class);
		return list;
	}
	
	
	public List<Notification> fetchNotifications4DashBoard(String userid) {		
		Query query = new Query(Criteria.where("status").is(1)
				.and("userid").in((Object[]) userid.split(",")));
		query.sort().on("notifyEndDate", Order.ASCENDING);
		List<Notification> list = mongoTemplate.find(query, Notification.class);
		
		return list.size() > 2 ? list.subList(0, 2) :list;
	}

	/**
	 * @param list
	 */
	public void sendBulkNotification(List<Notification> list) {
		Multimap<String, Notification> map = HashMultimap.create();
		for (Notification notification : list) {
			map.put(notification.getUserid(), notification);
		}
		Query query = new Query(Criteria.where("id").in(map.keySet()));
		query.sort().on("username", Order.ASCENDING);
		List<Users> listUsers = (List<Users>) mongoTemplate.find(query,
				Users.class);

		/*
		 * mailService.sendNotificationMail("libms.system@gmail.com",
		 * user.getEmail(), "Notification Pending", stbN.toString());
		 */
		mailService.sendNotificationMail(listUsers, map);
	}

	/**
	 * @param notification
	 */
	public void deactivate(Notification not) {

		Query query = new Query(Criteria.where("id").in(
				(Object[]) not.getId().split(",")));
		List<Notification> list = mongoTemplate.find(query, Notification.class);
		for (Notification notification : list) {
			notification.setStatus((short) 2);
			notification.setModifiedDate(new Date());
		}

		/*
		 * Notification notification = mongoTemplate.findById(not.getId(),
		 * Notification.class); notification.setStatus((short) 2);
		 * notification.setModifiedDate(new Date());
		 */
		notificationRepository.save(list);
	}
}