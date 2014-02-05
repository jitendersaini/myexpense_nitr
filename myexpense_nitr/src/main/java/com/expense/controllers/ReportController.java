package com.expense.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.domains.Expense;
import com.expense.models.CustomUserResponse;
import com.expense.models.Report;
import com.expense.models.ReportData;
import com.expense.services.ReportService;
import com.expense.utils.AppConstants;

/**
 * @author jitender
 * 
 */
@Controller
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	private Map<Integer, Map<Integer, List<Expense>>> mainMap = new HashMap<Integer, Map<Integer, List<Expense>>>();

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/action")
	public String loadForm(Model model) {
		model.addAttribute("report", new Report());
		Map<Short, String> grouping = new HashMap<>();
		grouping.put((short) 1, "Category");
		grouping.put((short) 2, "Payment Mode");

		model.addAttribute("grouping", grouping);
		return AppConstants.REPORTS_LIST;
	}

	@RequestMapping(value = "/action", params = { "jqgrid" })
	public @ResponseBody
	CustomUserResponse loadJson(HttpServletRequest req,
			@RequestParam Integer year, @RequestParam Integer month) {

		if (null == month && null == year) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
		}
		List<ReportData> list = reportService.getAllExpense(this.mainMap.get(
				year).get(month));

		CustomUserResponse response = new CustomUserResponse();

		response.setRows(list);
		response.setRecords(String.valueOf(list.size()));
		response.setPage("1");
		response.setTotal("10");
		return response;
	}

	/**
	 * @param model
	 * @param username
	 * @param report
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "search" })
	public String loadTable(HttpServletRequest req, Model model, Report report) {
		Map<Integer, Map<Integer, List<Expense>>> map = reportService
				.getExpense4Report(req.getSession().getAttribute("userid")
						.toString(), report);
		Set<Integer> set = map.keySet();
		model.addAttribute("list", set);
		model.addAttribute("grouping", report.getGrouping() == 1 ? "category"
				: "paidVia");
		Map<Integer, Set<Integer>> mp = new HashMap<Integer, Set<Integer>>();
		for (Integer year : set) {
			mp.put(year, map.get(year).keySet());

		}
		model.addAttribute("listkeys", mp);
		model.addAttribute("map", map);
		String[] arr = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		model.addAttribute("arr", arr);
		model.addAttribute(
				"budgets",
				reportService.getBudgets4Report(req.getSession()
						.getAttribute("userid").toString()));
		this.mainMap = map;

		return AppConstants.REPORTS_DATA_TABLE;
	}
}
