/**
 * 
 */
package com.expense.models;

import java.util.ArrayList;
import java.util.Date;

import com.expense.domains.Users;

/**
 * @author jitender.saini
 * 
 */
public class CategoryModal {

	private ArrayList<String> id;
	private ArrayList<String> userid;
	private ArrayList<Users> usersModified;

	private ArrayList<String> categoryName;
	private ArrayList<Date> createdDate;
	private ArrayList<Date> modifiedDate;
	private ArrayList<Short> categoryType;
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
	 * @return the categoryName
	 */
	public ArrayList<String> getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(ArrayList<String> categoryName) {
		this.categoryName = categoryName;
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
	 * @return the categoryType
	 */
	public ArrayList<Short> getCategoryType() {
		return categoryType;
	}

	/**
	 * @param categoryType
	 *            the categoryType to set
	 */
	public void setCategoryType(ArrayList<Short> categoryType) {
		this.categoryType = categoryType;
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
