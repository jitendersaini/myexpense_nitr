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
public class Notification implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2902716404775112857L;

	@Id
	private String id;
	private String categoryid;
	private String userid;
	@DBRef
	private Users usersModified;

	private String notifyTitle;
	private Date notifyStartDate;
	private Date notifyEndDate;
	private Date notifyDueDate;
	private Short notifyDays;
	private Short notifyVia;
	private Short status; // 1-active, 0-inactive
	private Date createdDate;
	private Date modifiedDate;
	@DBRef
	private Category category;
	@DBRef
	private Users users;

	public Notification() {
	}

	public Notification(String id) {
		this.id = id;
	}

	public Notification(String id, String categoryid, String userid,
			String notifyTitle, Date notifyStartDate, Date notifyEndDate,
			Short notifyDays, Short notifyVia, Date createdDate,
			Date modifiedDate, Category category, Users users) {
		super();
		this.id = id;
		this.categoryid = categoryid;
		this.userid = userid;
		this.notifyTitle = notifyTitle;
		this.notifyStartDate = notifyStartDate;
		this.notifyEndDate = notifyEndDate;
		this.notifyDays = notifyDays;
		this.notifyVia = notifyVia;
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
	 * @return the notifyTitle
	 */
	public String getNotifyTitle() {
		return notifyTitle;
	}

	/**
	 * @param notifyTitle
	 *            the notifyTitle to set
	 */
	public void setNotifyTitle(String notifyTitle) {
		this.notifyTitle = notifyTitle;
	}

	/**
	 * @return the notifyStartDate
	 */
	public Date getNotifyStartDate() {
		return notifyStartDate;
	}

	/**
	 * @param notifyStartDate
	 *            the notifyStartDate to set
	 */
	public void setNotifyStartDate(Date notifyStartDate) {
		this.notifyStartDate = notifyStartDate;
	}

	/**
	 * @return the notifyEndDate
	 */
	public Date getNotifyEndDate() {
		return notifyEndDate;
	}

	/**
	 * @param notifyEndDate
	 *            the notifyEndDate to set
	 */
	public void setNotifyEndDate(Date notifyEndDate) {
		this.notifyEndDate = notifyEndDate;
	}

	/**
	 * @return the notifyDays
	 */
	public Short getNotifyDays() {
		return notifyDays;
	}

	/**
	 * @param notifyDays
	 *            the notifyDays to set
	 */
	public void setNotifyDays(Short notifyDays) {
		this.notifyDays = notifyDays;
	}

	/**
	 * @return the notifyVia
	 */
	public Short getNotifyVia() {
		return notifyVia;
	}

	/**
	 * @param notifyVia
	 *            the notifyVia to set
	 */
	public void setNotifyVia(Short notifyVia) {
		this.notifyVia = notifyVia;
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
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * @return the notifyDueDate
	 */
	public Date getNotifyDueDate() {
		return notifyDueDate;
	}

	/**
	 * @param notifyDueDate
	 *            the notifyDueDate to set
	 */
	public void setNotifyDueDate(Date notifyDueDate) {
		this.notifyDueDate = notifyDueDate;
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

}
