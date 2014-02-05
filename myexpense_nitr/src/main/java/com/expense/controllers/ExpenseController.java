package com.expense.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.domains.Budget;
import com.expense.domains.Category;
import com.expense.domains.Expense;
import com.expense.domains.PaymentMode;
import com.expense.domains.Users;
import com.expense.models.ExpenseModal;
import com.expense.services.ExpenseService;
import com.expense.services.PayModeService;
import com.expense.utils.AppConstants;

/**
 * @author jitender
 * 
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private PayModeService payModeService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/action")
	public String loadForm(Model model, HttpServletRequest request) {

		Calendar calendar = Calendar.getInstance();

		List<Budget> budget = expenseService.getBudget(request.getSession()
				.getAttribute("userid").toString(),
				calendar.get(java.util.Calendar.MONTH) + 1,
				calendar.get(java.util.Calendar.YEAR));

		List<Budget> listMonYrs = expenseService.getMonYrs(request.getSession()
				.getAttribute("userid").toString());
		if (null != listMonYrs && listMonYrs.isEmpty()) {
			Budget b = new Budget();
			b.setMonth(calendar.get(java.util.Calendar.MONTH) + 1);
			b.setYear(calendar.get(java.util.Calendar.YEAR));
			listMonYrs.add(b);
		}
		model.addAttribute("interval", listMonYrs);
		model.addAttribute("MONTHS_ARRAY", AppConstants.MONTHS_ARRAY);

		model.addAttribute("budget",
				(budget != null && !budget.isEmpty()) ? budget.get(0)
						: new Budget());

		return AppConstants.EXPENSE_LIST;
	}

	/**
	 * @param model
	 * @param budget
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "savebudget" })
	public @ResponseBody
	String saveBudget(Model model, Budget budget, HttpServletRequest request) {
		expenseService.saveBudget(budget,
				request.getSession().getAttribute("userid").toString());
		return "Saved Successfully";
	}

	/*
	*//**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "create" })
	public String createExpense(Model model, HttpServletRequest request) {
		model.addAttribute(
				"map",
				expenseService.fetchCategories(request.getSession()
						.getAttribute("userid").toString()));
		model.addAttribute(
				"mapPaidVia",
				payModeService.getAllPaymentModesValues(request.getSession()
						.getAttribute("userid").toString()));
		model.addAttribute("expense", new ExpenseModal());
		return AppConstants.EXPENSE_CREATE;
	}

	/**
	 * @param model
	 * @param expense
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "save" })
	String save(Model model, Expense expense) {
		expense.setCategory(new Category(expense.getCategoryid()));
		expense.setPaidVia(new PaymentMode(expense.getPaidViaId()));
		expenseService.save(expense);
		return AppConstants.EXPENSE_DATA_TABLE;
	}

	@RequestMapping(value = "/action", params = { "savemultiple" })
	String saveMultipleExpense(ExpenseModal expense, HttpServletRequest request) {
		ArrayList<Category> l = null;
		for (String catId : expense.getCategoryid()) {
			l = new ArrayList<>();
			l.add(new Category(catId));
		}
		expense.setCategory(l);
		expenseService.saveMultiple(expense,
				request.getSession().getAttribute("login_id").toString());
		return AppConstants.EXPENSE_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "search" })
	public String loadTable(Model model, HttpServletRequest request,
			@RequestParam String interval) {

		String[] arrMOnthYr = interval.split("-");
		Calendar calendar1 = new GregorianCalendar(
				Integer.parseInt(arrMOnthYr[1]),
				Integer.parseInt(arrMOnthYr[0]) - 1, Calendar.DATE);
		calendar1.set(Calendar.DATE, calendar1.getActualMinimum(Calendar.DATE));

		Calendar calendar2 = new GregorianCalendar(
				Integer.parseInt(arrMOnthYr[1]),
				Integer.parseInt(arrMOnthYr[0]) - 1, Calendar.DATE);
		calendar2.set(Calendar.DATE, calendar2.getActualMaximum(Calendar.DATE));

		Calendar calendar = Calendar.getInstance();

		List<Budget> budget = expenseService.getBudget(request.getSession()
				.getAttribute("userid").toString(),
				calendar.get(java.util.Calendar.MONTH) + 1,
				calendar.get(java.util.Calendar.YEAR));

		Double mBudget = 0.00;
		Double amnt = 0.00;
		if (null != budget && !budget.isEmpty()) {
			mBudget = budget.get(0).getValue();
		}
		List<Expense> list = expenseService.getAllExpense(request.getSession()
				.getAttribute("userid").toString(), calendar1.getTime(),
				calendar2.getTime());

		List<Boolean> listMB = new ArrayList<Boolean>();
		for (Expense expense : list) {
			amnt += expense.getExpenseValue();
			if (amnt > mBudget) {
				listMB.add(true);
			} else {
				listMB.add(false);
			}
		}
		DecimalFormat df = new DecimalFormat("#.##");
		if (amnt > mBudget) {
			model.addAttribute("balance",
					Double.valueOf(df.format(amnt - mBudget)));
		}
		model.addAttribute("amount", Double.valueOf(df.format(amnt)));
		model.addAttribute("listMB", listMB);
		model.addAttribute("list", list);
		return AppConstants.EXPENSE_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param expense
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "edit" })
	public String editExpense(Model model, Expense expense,
			HttpServletRequest request) {
		expense.setUsers(new Users(expense.getUserid()));
		model.addAttribute(
				"map",
				expenseService.fetchCategories(request.getSession()
						.getAttribute("userid").toString()));
		model.addAttribute("mapPaidVia",
				payModeService.getAllPaymentModesValues(expense.getUserid()));
		model.addAttribute("expense", expense);
		return AppConstants.EXPENSE_MODIFY;
	}

	/**
	 * @param expense
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "remove" })
	public String removeExpense(Expense expense) {
		expenseService.remove(expense);
		return AppConstants.EXPENSE_DATA_TABLE;
	}

}