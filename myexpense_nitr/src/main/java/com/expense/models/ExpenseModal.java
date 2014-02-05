package com.expense.models;

import java.util.ArrayList;
import java.util.Date;

import com.expense.domains.Category;
import com.expense.domains.PaymentMode;
import com.expense.domains.Users;

/**
 * @author jitender
 * 
 */

public class ExpenseModal {

	private ArrayList<String> id;
	private ArrayList<String> categoryid;
	private ArrayList<String> paidViaId;
	
	private ArrayList<String> userid;
	private ArrayList<Users> usersModified;
	
	private ArrayList<String> expenseTitle;
	private ArrayList<Double> expenseValue;
	
	private ArrayList<PaymentMode> paidVia;
	
	private ArrayList<Date> expenseDate;
	private ArrayList<Date> createdDate;
	private ArrayList<Date> modifiedDate;
	
	private ArrayList<Category> category;
	private ArrayList<Users> users;

	/**
	 * @return the id
	 */
	public ArrayList<String> getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(ArrayList<String> id) {
		this.id = id;
	}

	/**
	 * @return the categoryid
	 */
	public ArrayList<String> getCategoryid() {
		return categoryid;
	}

	/**
	 * @param categoryid
	 *            the categoryid to set
	 */
	public void setCategoryid(ArrayList<String> categoryid) {
		this.categoryid = categoryid;
	}

	/**
	 * @return the userid
	 */
	public ArrayList<String> getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(ArrayList<String> userid) {
		this.userid = userid;
	}

	/**
	 * @return the usersModified
	 */
	public ArrayList<Users> getUsersModified() {
		return usersModified;
	}

	/**
	 * @param usersModified
	 *            the usersModified to set
	 */
	public void setUsersModified(ArrayList<Users> usersModified) {
		this.usersModified = usersModified;
	}

	/**
	 * @return the expenseTitle
	 */
	public ArrayList<String> getExpenseTitle() {
		return expenseTitle;
	}

	/**
	 * @param expenseTitle
	 *            the expenseTitle to set
	 */
	public void setExpenseTitle(ArrayList<String> expenseTitle) {
		this.expenseTitle = expenseTitle;
	}

	/**
	 * @return the expenseValue
	 */
	public ArrayList<Double> getExpenseValue() {
		return expenseValue;
	}

	/**
	 * @param expenseValue
	 *            the expenseValue to set
	 */
	public void setExpenseValue(ArrayList<Double> expenseValue) {
		this.expenseValue = expenseValue;
	}

	

	/**
	 * @return the paidViaId
	 */
	public ArrayList<String> getPaidViaId() {
		return paidViaId;
	}

	/**
	 * @param paidViaId the paidViaId to set
	 */
	public void setPaidViaId(ArrayList<String> paidViaId) {
		this.paidViaId = paidViaId;
	}

	/**
	 * @return the paidVia
	 */
	public ArrayList<PaymentMode> getPaidVia() {
		return paidVia;
	}

	/**
	 * @param paidVia the paidVia to set
	 */
	public void setPaidVia(ArrayList<PaymentMode> paidVia) {
		this.paidVia = paidVia;
	}

	/**
	 * @return the expenseDate
	 */
	public ArrayList<Date> getExpenseDate() {
		return expenseDate;
	}

	/**
	 * @param expenseDate
	 *            the expenseDate to set
	 */
	public void setExpenseDate(ArrayList<Date> expenseDate) {
		this.expenseDate = expenseDate;
	}

	/**
	 * @return the createdDate
	 */
	public ArrayList<Date> getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(ArrayList<Date> createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public ArrayList<Date> getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(ArrayList<Date> modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the category
	 */
	public ArrayList<Category> getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(ArrayList<Category> category) {
		this.category = category;
	}

	/**
	 * @return the users
	 */
	public ArrayList<Users> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}

}
