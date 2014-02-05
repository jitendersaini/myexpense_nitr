package com.expense.controllers;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expense.domains.Expense;
import com.expense.services.ChartService;
import com.expense.services.NotificationService;
import com.expense.utils.AppConstants;

/**
 * @author jitender.saini
 * 
 */
@Controller
@RequestMapping("/dashboard")
public class DashBoardController {

	@Autowired
	private ChartService chartService;

	@Autowired
	private NotificationService notificationService;

	
	
	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action")
	public String loadForm(Model model, HttpServletRequest request) {		
		return AppConstants.DASHBOARD_LIST;
	}
	
	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "search" })
	public String searchForData(Model model, HttpServletRequest request) {
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Map<Integer, List<Expense>> map = chartService.getDataForYear(request
				.getSession().getAttribute("userid").toString(), year);
		Map<Integer, Double> map1 = chartService.getBudgetsForYear(request
				.getSession().getAttribute("userid").toString(), year);
		TreeSet<Double> set = getTreeSet(map);
		model.addAttribute("maxValue", CollectionUtils.isEmpty(set) ? "0.00"
				: set.last());
		Map<Integer, Double> mp = getMap(map);
		Set<Integer> setKeys = map1.keySet();
		Set<Integer> setKeysExp = mp.keySet();
		DecimalFormat df = new DecimalFormat("##.00");
		Double currExp = setKeysExp.size() > 0 ? mp
				.get(setKeysExp.toArray()[setKeysExp.size() - 1]) : 0;
		String strCurrExp = null;
		if (Math.floor(currExp.doubleValue()) == currExp.doubleValue()) {
			strCurrExp = currExp.intValue() + "";
		} else {
			strCurrExp = df.format(currExp);
		}
		int currBudg = setKeys.size() > 0 ? map1.get(
				setKeys.toArray()[setKeys.size() - 1]).intValue() : 0;
		char _color_code = 'G';
		if (currExp > currBudg) {
			_color_code = 'R';
		} else if (currExp > currBudg / 2 && currExp <= currBudg) {
			_color_code = 'A';
		}
		model.addAttribute("currentMonthBudget", currBudg);
		model.addAttribute("currentMonthExp", strCurrExp);
		model.addAttribute("_color_code", _color_code);
		model.addAttribute("budget", map1);
		model.addAttribute("year", year);
		model.addAttribute("map", mp);
		model.addAttribute("keys", mp.keySet());
		model.addAttribute(
				"list",
				notificationService.fetchNotifications4DashBoard(request
						.getSession().getAttribute("userid").toString()));
		model.addAttribute("MONTHS_ARRAY", AppConstants.MONTHS_ARRAY);
		return AppConstants.DASHBOARD_DATA;
	}

	/**
	 * @param map
	 * @return
	 */
	private TreeSet<Double> getTreeSet(Map<Integer, List<Expense>> map) {
		TreeSet<Double> ts = new TreeSet<Double>();
		for (Integer month : map.keySet()) {
			Double value = 0.00;
			for (Expense expense : map.get(month)) {
				value += expense.getExpenseValue();
			}
			ts.add(value);

		}
		return ts;
	}

	/**
	 * @param map
	 * @return
	 */
	private Map<Integer, Double> getMap(Map<Integer, List<Expense>> map) {
		Map<Integer, Double> mp = new HashMap<Integer, Double>();
		for (Integer month : map.keySet()) {
			Double value = 0.00;
			for (Expense expense : map.get(month)) {
				value += expense.getExpenseValue();
			}
			mp.put(month, value);

		}
		return mp;
	}
}
