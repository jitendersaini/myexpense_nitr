/**
 * 
 */
package com.expense.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author jitender
 * 
 */
public class AppConstants {
	
	public static Map<Short, String> getPaidViaMap() {
		Map<Short, String> mapPaidVia = new HashMap<>();
		Short i = 0;
		for (String payment_mode : AppConstants.PAYMENT_MODE_ARRAY) {
			mapPaidVia.put(i, payment_mode);
			i++;
		}
		return mapPaidVia;
	}
	
	
	/** Payment Mode Array */
	public static String[] PAYMENT_MODE_ARRAY = { "Cash", "Credit Card", "Debit Card",
		"Cheque", "Coupons"};
	
	/** Month Array */
	public static String[] MONTHS_ARRAY = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	/** Login */
	public static final String LOGIN_PAGE = "login-page/login";
	
	/** Dash Board */
	public static final String DASHBOARD_LIST = "dashboard-page/dashboard_list";
	public static final String DASHBOARD_DATA = "dashboard-page/dashboard_data";
	
	
	/** Payment Mode */
	public static final String PAYMODE_LIST = "paymode-page/paymode_list";
	public static final String PAYMODE_CREATE = "paymode-page/paymode_create";
	public static final String PAYMODE_DATA_TABLE = "paymode-page/paymode_data";
	public static final String PAYMODE_MODIFY = "paymode-page/paymode_modify";
	
	/** Category */
	public static final String CATEGORY_LIST = "category-page/category_list";
	public static final String CATEGORY_DATA_TABLE = "category-page/category_data";
	public static final String CATEGORY_CREATE = "category-page/category_create";
	public static final String CATEGORY_MODIFY = "category-page/category_modify";

	/** Users */
	public static final String USERS_LIST = "users-page/users_list";
	public static final String USERS_DATA_TABLE = "users-page/users_data";
	public static final String USERS_CREATE = "users-page/users_create";
	public static final String USERS_EDIT = "users-page/users_edit";

	/** Expense */
	public static final String EXPENSE_LIST = "expense-page/expense_list";
	public static final String EXPENSE_CREATE = "expense-page/expense_create";
	public static final String EXPENSE_DATA_TABLE = "expense-page/expense_data";
	public static final String EXPENSE_MODIFY = "expense-page/expense_modify";

	/** Notifications */
	public static final String NOTIFICATION_LIST = "notification-page/notification_list";
	public static final String NOTIFICATION_DATA_TABLE = "notification-page/notification_data";
	public static final String NOTIFICATION_CREATE = "notification-page/notification_create";
	public static final String NOTIFICATION_MODIFY = "notification-page/notification_modify";
	/*public static final String EXPENSE_CREATE = "expense-page/expense_create";
	public static final String EXPENSE_DATA_TABLE = "expense-page/expense_data";*/
	
	
	/** Reports */
	public static final String REPORTS_LIST = "reports-page/report_list";
	public static final String REPORTS_DATA_TABLE = "reports-page/report_data";

	/** Bar Charts */
	public static final String YEARLY_BAR_CHART_LIST = "charts-page/yearly_bar_chart_list";;
	public static final String YEARLY_BAR_CHART = "charts-page/yearly_bar_chart";
	public static final String MONTHLY_BAR_CHART = "charts-page/monthly_bar_chart";
	
	/** Pie Charts */
	public static final String MONTHLY_PIE_CHART_LIST = "charts-page/monthly_pie_chart_list";;	
	
	/** FORGET PASSWORD */
	public static final String SEND_EMAIL = "users-page/send_email";

	/** CHANGE PASSWORD */
	public static final String CHANGE_PASSWORD = "users-page/change_password";

	

}
