package com.expense.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jitender
 * 
 */
@Document
public class Budget implements java.io.Serializable {

	private static final long serialVersionUID = -2794218166406976287L;
	@Id
	private String id;
	private String userid;

	@DBRef
	private Users usersModified;

	private Double value;
	private Integer month;
	private Integer year;
	@DBRef
	private Users users;

	public Budget() {
	}

	

	/**
	 * @param id
	 * @param userid
	 * @param usersModified
	 * @param value
	 * @param month
	 * @param year
	 * @param users
	 */
	public Budget(String id, String userid, Users usersModified, Double value,
			Integer month, Integer year, Users users) {		
		this.id = id;
		this.userid = userid;
		this.usersModified = usersModified;
		this.value = value;
		this.month = month;
		this.year = year;
		this.users = users;
	}



	public Budget(String id) {
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
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
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
	 * @param usersModified
	 */
	public void setUsersModified(Users usersModified) {
		this.usersModified = usersModified;
	}

	/**
	 * @return
	 */
	public Users getUsersModified() {
		return usersModified;
	}

}
