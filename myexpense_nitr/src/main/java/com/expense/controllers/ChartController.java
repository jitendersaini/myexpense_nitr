package com.expense.controllers;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expense.domains.Budget;
import com.expense.domains.Expense;
import com.expense.services.ChartService;
import com.expense.services.ExpenseService;
import com.expense.utils.AppConstants;

/**
 * @author jitender
 * 
 */
@Controller
@RequestMapping("/bargraph")
public class ChartController {

	@Autowired
	private ChartService chartService;

	@Autowired
	private ExpenseService expenseService;

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action")
	public String loadForm(Model model, HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		List<Integer> budgets = chartService.getBudgetsForUser(request
				.getSession().getAttribute("userid").toString());
		List<Budget> listMonYrs = expenseService.getMonYrs(request.getSession()
				.getAttribute("userid").toString());
		if (null != listMonYrs && listMonYrs.isEmpty()) {
			Budget b = new Budget();
			b.setMonth(calendar.get(java.util.Calendar.MONTH) + 1);
			b.setYear(calendar.get(java.util.Calendar.YEAR));
			listMonYrs.add(b);
		}
		Map<Short, String> grouping = new HashMap<>();
		grouping.put((short) 1, "Category");
		grouping.put((short) 2, "Payment Mode");

		model.addAttribute("grouping", grouping);
		Collections.sort(budgets, Collections.reverseOrder());
		model.addAttribute("MONTHS_ARRAY", AppConstants.MONTHS_ARRAY);
		model.addAttribute("interval", listMonYrs);
		model.addAttribute("budgets", budgets);
		return AppConstants.YEARLY_BAR_CHART_LIST;
	}

	/**
	 * @param model
	 * @param request
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "search_year" })
	public String loadTable(Model model, HttpServletRequest request,
			@RequestParam Integer year) {
		Map<Integer, List<Expense>> map = chartService.getDataForYear(request
				.getSession().getAttribute("userid").toString(), year);
		Map<Integer, Double> map1 = chartService.getBudgetsForYear(request
				.getSession().getAttribute("userid").toString(), year);
		TreeSet<Double> set = getTreeSet(map);
		model.addAttribute("maxValue", CollectionUtils.isEmpty(set) ? "0.00"
				: set.last());
		Map<Integer, Double> mp = getMap(map);
		model.addAttribute("budget", map1);
		model.addAttribute("year", year);
		model.addAttribute("map", mp);
		model.addAttribute("keys", mp.keySet());
		model.addAttribute("MONTHS_ARRAY", AppConstants.MONTHS_ARRAY);
		return AppConstants.YEARLY_BAR_CHART;
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

	/**
	 * @param model
	 * @param request
	 * @param interval
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "pie_chart" })
	public String loadPieChart(Model model, HttpServletRequest request,
			@RequestParam String interval, @RequestParam String grouping) {
		Calendar calendar = Calendar.getInstance();

		Integer month = null;
		Integer year = null;
		if (StringUtils.isNotEmpty(interval)) {
			String[] arr = interval.split("-");
			month = Integer.parseInt(arr[0]) - 1;
			year = Integer.parseInt(arr[1]);
		} else {
			month = calendar.get(Calendar.MONTH);
			year = calendar.get(Calendar.YEAR);
		}
		Calendar calendar1 = new GregorianCalendar(year, month, Calendar.DATE);
		calendar1.set(Calendar.DATE, calendar1.getActualMinimum(Calendar.DATE));

		Calendar calendar2 = new GregorianCalendar(year, month, Calendar.DATE);
		calendar2.set(Calendar.DATE, calendar2.getActualMaximum(Calendar.DATE));

		List<Expense> list = expenseService.getAllExpense(request.getSession()
				.getAttribute("userid").toString(), calendar1.getTime(),
				calendar2.getTime());

		Map<String, Double> map = new HashMap<String, Double>();
		Map<String, String> catIdValue = new HashMap<String, String>();

		for (Expense expense : list) {
			if (null != map.get("total")) {
				map.put("total", (map.get("total") + expense.getExpenseValue()));
			} else {
				map.put("total", expense.getExpenseValue());
			}
			if (StringUtils.isEmpty(grouping) || grouping.equalsIgnoreCase("1")) {
				if (null != map.get(expense.getCategoryid())) {
					map.put(expense.getCategoryid(), (map.get(expense
							.getCategoryid()) + expense.getExpenseValue()));
				} else {
					map.put(expense.getCategoryid(), expense.getExpenseValue());
				}
				catIdValue.put(expense.getCategoryid(), expense.getCategory()
						.getCategoryName());
			} else {
				if (null != map.get(expense.getPaidVia().getId())) {
					map.put(expense.getPaidVia().getId(), (map.get(expense
							.getPaidVia().getId()) + expense.getExpenseValue()));
				} else {
					map.put(expense.getPaidVia().getId(),
							expense.getExpenseValue());
				}
				catIdValue.put(expense.getPaidVia().getId(), expense
						.getPaidVia().getPaymentMode());
			}
		}

		model.addAttribute("map", map);
		model.addAttribute("catIdValue", catIdValue);
		return AppConstants.MONTHLY_PIE_CHART_LIST;
	}
}
