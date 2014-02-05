package com.expense.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.domains.Users;
import com.expense.models.ChangePassword;
import com.expense.services.MailService;
import com.expense.services.UsersService;
import com.expense.utils.AppConstants;

/**
 * @author jitender
 * 
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private MailService mailService;

	/**
	 * @param model
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/action", params = { "frgtpass" }) public String
	 * forgetUserPassword(Model model) { model.addAttribute("users", new
	 * Users()); return AppConstants.SEND_EMAIL; }
	 */

	@RequestMapping(value = "/action", params = { "getall" })
	public String getUsers(Model model) {
		List<Users> list = usersService.readAll();
		model.addAttribute("list", list);
		return "employee_views";
	}

	/**
	 * @param model
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "save" })
	public @ResponseBody
	String saveUsers(Model model, Users users) {
		return usersService.save(users);
	}

	/**
	 * @param model
	 * @param users
	 * @param req
	 * @return
	 */

	@RequestMapping(value = "/action", params = { "savechagepass" })
	public @ResponseBody
	Integer savePasswordChanged(Model model, ChangePassword cp,
			HttpServletRequest req) {

		return usersService.savePasswordChanged(cp);
	}

	@RequestMapping(value = "/action", params = { "remove" })
	public String removeUsers(Users users) {
		usersService.remove(users);
		return AppConstants.USERS_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param users
	 * @return
	 */

	@RequestMapping(value = "/action", params = { "edit" })
	public String editUsers(Model model) {
		model.addAttribute("users", new Users());
		return AppConstants.USERS_EDIT;
	}

	@RequestMapping(value = "/action", params = { "loadchangepass" })
	public String changePassword(Model model) {
		model.addAttribute("changepassword", new ChangePassword());
		return AppConstants.CHANGE_PASSWORD;
	}

	@RequestMapping(value = "/action", params = { "sendemail" })
	public @ResponseBody
	String sendEmail(Users users, Model model) {
		int password = usersService.resetAndSavePassword(users.getEmail());
		if (password != 0) {
			mailService.sendMail("libms.system@gmail.com", users.getEmail(),
					"Password Reset",
					"Your Password has been reset! Your New Password is: "
							+ password);
			return "done";
		} else {
			return "not done";
		}
	}

	@RequestMapping(value = "/action", params = { "findacc" })
	public @ResponseBody
	String[] findJoinAccount(String usernameOrEmail, String joinPassword) {		
		Users users = usersService.findAccountById(usernameOrEmail,joinPassword);
		if(null != users && !users.getJoinedid().equals("")) {
			return new String[] {"child"};
		}
		return users != null ? (new String[] { users.getId(),
				users.getEmail(),users.getCurrency() }) : null;
	}

}
