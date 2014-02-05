package com.expense.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expense.domains.Users;
import com.expense.repositories.UserRepository;

/**
 * @author jitender
 * 
 */
@Controller
public class ModuleController {
	/*
	 * @Autowired private UsersService usersService;
	 */

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("_admin")
	public String admin(HttpServletRequest request) {
		request.getSession().invalidate();
		if (request.getSession().getAttribute("userid") == null) {
			Users usr = userRepository.findByUsername(SecurityContextHolder
					.getContext().getAuthentication().getName());

			//StringBuilder combinedUserId = new StringBuilder(usr.getId());
			StringBuilder combinedUserId = new StringBuilder();
			if (StringUtils.isNotEmpty(usr.getJoinedid())) {
				Users primaryUsrId = userRepository.findById(usr
						.getJoinedid());				
				combinedUserId.append(primaryUsrId.getId());
				List<Users> usrJoined = userRepository.findByJoinedid(usr
						.getJoinedid());
				if (null != usrJoined) {
					for (Users users : usrJoined) {						
						combinedUserId.append(",");
						combinedUserId.append(users.getId());
					}
				}				
				Users users = userRepository.findById(usr.getJoinedid());				
				request.getSession().setAttribute("joinedName", users.getName());
			} else {
				combinedUserId.append(usr.getId());
				List<Users> usrJoined = userRepository.findByJoinedid(usr
						.getId());
				if (null != usrJoined) {
					for (Users users : usrJoined) {						
						combinedUserId.append(",");
						combinedUserId.append(users.getId());
					}
				}
			}

			request.getSession().setAttribute("userid", combinedUserId);
			request.getSession().setAttribute("email", usr.getEmail());
			request.getSession().setAttribute("name", usr.getName());
			request.getSession().setAttribute("currency", usr.getCurrency());
			request.getSession()
					.setAttribute("colortheme", usr.getColorTheme());
			request.getSession().setAttribute("login_id", usr.getId());			
			request.getSession().setAttribute("join_id", usr.getJoinedid());
		}
		return "admin-page/_admin";
	}

	@RequestMapping("_login")
	public String login() {
		return "login-page/login";
	}

}
