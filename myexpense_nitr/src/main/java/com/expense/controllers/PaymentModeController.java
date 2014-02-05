package com.expense.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.domains.Expense;
import com.expense.domains.PaymentMode;
import com.expense.domains.Users;
import com.expense.models.PaymentModeModal;
import com.expense.services.ExpenseService;
import com.expense.services.PayModeService;
import com.expense.utils.AppConstants;

/**
 * @author jitender
 * 
 */
/**
 * @author jitender.saini
 * 
 */
@Controller
@RequestMapping("/paymode")
public class PaymentModeController {

	@Autowired
	private PayModeService payModeService;

	@Autowired
	private ExpenseService expenseService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/action")
	public String loadForm() {
		return AppConstants.PAYMODE_LIST;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "search" })
	public String loadTable(Model model, @RequestParam String userid) {
		List<PaymentMode> list = payModeService.getAllPaymentModes(userid);
		model.addAttribute("list", list);
		model.addAttribute("PAYMENT_MODE_ARRAY",
				AppConstants.PAYMENT_MODE_ARRAY);

		return AppConstants.PAYMODE_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "create" })
	public String createPayMode(Model model) {
		model.addAttribute("mapPaidVia", AppConstants.getPaidViaMap());
		model.addAttribute("paymode", new PaymentModeModal());
		return AppConstants.PAYMODE_CREATE;
	}

	@RequestMapping(value = "/action", params = { "savemultiple" })
	public String saveMutiplePaymentMode(PaymentModeModal paymentModeModal,
			HttpServletRequest request) {
		payModeService.saveMultiple(paymentModeModal, request.getSession()
				.getAttribute("login_id").toString());
		return AppConstants.PAYMODE_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "save" })
	public String savePaymentMode(PaymentMode paymentMode) {
		payModeService.save(paymentMode);
		return AppConstants.PAYMODE_DATA_TABLE;
	}

	/**
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "remove" })
	public @ResponseBody
	String removePayMode(Model model, PaymentMode paymode) {
		List<Expense> list = payModeService
				.isPaymentModeMapped(paymode.getId());
		if (list == null || list.isEmpty()) {
			payModeService.remove(paymode);
			return "";
		} else {
			StringBuilder stb = new StringBuilder();
			for (Expense expense : list) {
				stb.append(expense.getCategory().getCategoryName());
				stb.append(" | ");
			}
			String str = stb.toString();
			return (str.substring(0, str.lastIndexOf(" | ")));
		}
	}

	/**
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/action", params = { "edit" })
	public String editPayment(Model model, PaymentMode paymode) {
		paymode.setUsers(new Users(paymode.getUserid()));
		if (expenseService.isPaymentModeIdMapped(paymode.getId()) == null) {
			model.addAttribute("isPayModeMapped", false);
		} else {
			model.addAttribute("isPayModeMapped", true);
		}

		model.addAttribute("map", AppConstants.getPaidViaMap());
		model.addAttribute("paymode", paymode);
		return AppConstants.PAYMODE_MODIFY;
	}
}
