package com.expense.domains;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jitender.saini
 * 
 */
@Document
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 4210737077397468888L;
	@Id
	private String id;
	private String userid;
	@DBRef
	private Users usersModified;

	private String categoryName;
	private Date createdDate;
	private Date modifiedDate;
	private Short categoryType;
	@DBRef
	private Users users;

	public Category() {

	}

	/**
	 * @param id
	 * @param usersModified
	 * @param userid
	 * @param categoryName
	 * @param createdDate
	 * @param modifiedDate
	 * @param users
	 */
	public Category(String id, Users usersModified, String userid,
			String categoryName, Date createdDate, Date modifiedDate,
			Users users) {
		this.id = id;
		this.usersModified = usersModified;
		this.categoryName = categoryName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.users = users;
		this.userid = userid;
	}

	/**
	 * @param id
	 */
	public Category(String id) {
		this.id = id;
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	 * @return the categoryType
	 */
	public Short getCategoryType() {
		return categoryType;
	}

	/**
	 * @param categoryType
	 *            the categoryType to set
	 */
	public void setCategoryType(Short categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 * @return
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
