package com.expense.domains;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = -7570427901685906815L;

	@Id
	private String id;
	private String joinedid;
	private String username;
	private String password;
	private String name;
	private String email;
	private Integer access;
	private Integer enabled;
	private Integer deleted;
	private String currency;
	private String colorTheme;
	private Date createdDate;
	private Date modifiedDate;

	public Users() {

	}

	/**
	 * @param id
	 */
	public Users(String id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param access
	 * @param enabled
	 * @param deleted
	 * @param currency
	 * @param colorTheme
	 * @param createdDate
	 * @param modifiedDate
	 */
	public Users(String id, String username, String password, String name,
			String email, Integer access, Integer enabled, Integer deleted,
			String currency, String colorTheme, Date createdDate,
			Date modifiedDate,String joinedid) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.access = access;
		this.enabled = enabled;
		this.deleted = deleted;
		this.currency = currency;
		this.colorTheme = colorTheme;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.joinedid = joinedid;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the access
	 */
	public Integer getAccess() {
		return access;
	}

	/**
	 * @param access
	 *            the access to set
	 */
	public void setAccess(Integer access) {
		this.access = access;
	}

	/**
	 * @return the enabled
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the deleted
	 */
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the colorTheme
	 */
	public String getColorTheme() {
		return colorTheme;
	}

	/**
	 * @param colorTheme
	 *            the colorTheme to set
	 */
	public void setColorTheme(String colorTheme) {
		this.colorTheme = colorTheme;
	}

	/**
	 * @return the joinedId
	 */
	public String getJoinedid() {
		return joinedid;
	}

	/**
	 * @param joinedId
	 *            the joinedId to set
	 */
	public void setJoinedid(String joinedid) {
		this.joinedid = joinedid;
	}

}
