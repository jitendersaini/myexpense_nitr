package com.expense.domains;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jitender
 * 
 */
@Document
public class Expense implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3808459620025168427L;
	@Id
	private String id;
	private String categoryid;
	private String paidViaId;
	private String userid;
	@DBRef
	private Users usersModified;

	private String expenseTitle;
	private Double expenseValue;
	@DBRef
	private PaymentMode paidVia;
	private Date expenseDate;
	private Date createdDate;
	private Date modifiedDate;
	@DBRef
	private Category category;

	@DBRef
	private Users users;

	public Expense() {
	}

	public Expense(String id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param categoryid
	 * @param paidViaId
	 * @param userid
	 * @param usersModified
	 * @param expenseTitle
	 * @param expenseValue
	 * @param paidVia
	 * @param expenseDate
	 * @param createdDate
	 * @param modifiedDate
	 * @param category
	 * @param users
	 */
	public Expense(String id, String categoryid, String paidViaId,
			String userid, Users usersModified, String expenseTitle,
			Double expenseValue, PaymentMode paidVia, Date expenseDate,
			Date createdDate, Date modifiedDate, Category category, Users users) {
		super();
		this.id = id;
		this.categoryid = categoryid;
		this.paidViaId = paidViaId;
		this.userid = userid;
		this.usersModified = usersModified;
		this.expenseTitle = expenseTitle;
		this.expenseValue = expenseValue;
		this.paidVia = paidVia;
		this.expenseDate = expenseDate;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.category = category;
		this.users = users;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the categoryid
	 */
	public String getCategoryid() {
		return categoryid;
	}

	/**
	 * @param categoryid
	 *            the categoryid to set
	 */
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the expenseTitle
	 */
	public String getExpenseTitle() {
		return expenseTitle;
	}

	/**
	 * @param expenseTitle
	 *            the expenseTitle to set
	 */
	public void setExpenseTitle(String expenseTitle) {
		this.expenseTitle = expenseTitle;
	}

	/**
	 * @return the expenseValue
	 */
	public Double getExpenseValue() {
		return expenseValue;
	}

	/**
	 * @param expenseValue
	 *            the expenseValue to set
	 */
	public void setExpenseValue(Double expenseValue) {
		this.expenseValue = expenseValue;
	}

	/**
	 * @return the expenseDate
	 */
	public Date getExpenseDate() {
		return expenseDate;
	}

	/**
	 * @param expenseDate
	 *            the expenseDate to set
	 */
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @return
	 */
	public PaymentMode getPaidVia() {
		return paidVia;
	}

	/**
	 * @param paidVia
	 */
	public void setPaidVia(PaymentMode paidVia) {
		this.paidVia = paidVia;
	}

	/**
	 * @return
	 */
	public Users getUsersModified() {
		return usersModified;
	}

	/**
	 * @param usersModified
	 */
	public void setUsersModified(Users usersModified) {
		this.usersModified = usersModified;
	}

	/**
	 * @return the paidViaId
	 */
	public String getPaidViaId() {
		return paidViaId;
	}

	/**
	 * @param paidViaId
	 *            the paidViaId to set
	 */
	public void setPaidViaId(String paidViaId) {
		this.paidViaId = paidViaId;
	}

}
