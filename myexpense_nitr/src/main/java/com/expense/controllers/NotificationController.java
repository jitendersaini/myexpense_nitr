package com.expense.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expense.domains.Category;
import com.expense.domains.Notification;
import com.expense.domains.Users;
import com.expense.models.NotificationModal;
import com.expense.services.NotificationService;
import com.expense.utils.AppConstants;
import com.expense.utils.AppUtils;

/**
 * @author jitender
 * 
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/action")
	public String loadForm() {
		return AppConstants.NOTIFICATION_LIST;
	}

	/*
	*//**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "create" })
	public String createNotification(Model model, HttpServletRequest request) {
		model.addAttribute(
				"map",
				notificationService.fetchCategoriesNotification(request
						.getSession().getAttribute("userid").toString()));
		model.addAttribute("notification", new NotificationModal());
		Map<Short, String> map = new HashMap<>();
		map.put((short) 1, "Email");
		map.put((short) 2, "Sms");
		map.put((short) 3, "Both");
		model.addAttribute("mapNotifyVia", map);
		return AppConstants.NOTIFICATION_CREATE;
	}

	/**
	 * @param model
	 * @param notification
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "save" })
	String save(Model model, Notification notification) {
		notification.setStatus((short) 1);

		notification.setNotifyDueDate(AppUtils.getReducedDaysFromGivenDate(
				notification.getNotifyEndDate(), notification.getNotifyDays()));

		notification.setCategory(new Category(notification.getCategoryid()));
		notificationService.save(notification);
		return AppConstants.NOTIFICATION_DATA_TABLE;
	}

	@RequestMapping(value = "/action", params = { "savemultiple" })
	String saveMultiple(NotificationModal notification,
			HttpServletRequest request) {
		notificationService.saveMultiple(notification, request.getSession()
				.getAttribute("login_id").toString());
		return AppConstants.NOTIFICATION_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "search" })
	public String loadTable(Model model, HttpServletRequest request) {

		List<Notification> list = notificationService
				.getAllNotification(request.getSession().getAttribute("userid")
						.toString());
		model.addAttribute("list", list);
		List<String> statusDescription = new ArrayList<>();
		List<String> cssName = new ArrayList<>();
		for (Notification notification : list) {
			long dateDiff = AppUtils.getDateDifferenceAsDays(notification
					.getNotifyEndDate());

			if (dateDiff < 0 || notification.getStatus() == 2) {
				statusDescription.add("Deactivated");
				cssName.add("disabled");
			} else if (dateDiff <= notification.getNotifyDays()) {
				statusDescription.add("Due");
				cssName.add("gradeA");
			} else {
				statusDescription.add("Upcoming");
				cssName.add("gradeC");
			}
		}
		model.addAttribute("statusDescription", statusDescription);
		model.addAttribute("cssName", cssName);
		return AppConstants.NOTIFICATION_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param notification
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "edit" })
	public String editNotification(Model model, Notification notification,
			HttpServletRequest request) {
		notification.setUsers(new Users(notification.getUserid()));
		model.addAttribute(
				"map",
				notificationService.fetchCategoriesNotification(request
						.getSession().getAttribute("userid").toString()));
		model.addAttribute("notification", notification);
		Map<Short, String> map = new HashMap<>();
		map.put((short) 1, "Email");
		map.put((short) 2, "Sms");
		map.put((short) 3, "Both");
		model.addAttribute("mapNotifyVia", map);
		return AppConstants.NOTIFICATION_MODIFY;
	}

	/**
	 * @param notification
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "remove" })
	public String removeNotification(Notification notification) {
		notificationService.remove(notification);
		return AppConstants.NOTIFICATION_DATA_TABLE;
	}

	@RequestMapping(value = "/action", params = { "deactivate" })
	public String deactivateNotification(Notification notification) {
		notificationService.deactivate(notification);
		return AppConstants.NOTIFICATION_DATA_TABLE;
	}

}