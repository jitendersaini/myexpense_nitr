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
public class PaymentMode implements java.io.Serializable {

	private static final long serialVersionUID = 4210737077397468888L;
	@Id
	private String id;
	private String userid;
	@DBRef
	private Users usersModified;

	private String paymentMode;
	private Short paymentModeType;
	private Date createdDate;
	private Date modifiedDate;
	@DBRef
	private Users users;

	
	/**
	 * @param id
	 */
	public PaymentMode(String id) {
		super();
		this.id = id;
	}

	public PaymentMode() {

	}

	/**
	 * @param id
	 * @param userid
	 * @param usersModified
	 * @param paymentMode
	 * @param paymentModeType
	 * @param createdDate
	 * @param modifiedDate
	 * @param users
	 */
	public PaymentMode(String id, String userid, Users usersModified,
			String paymentMode, Short paymentModeType, Date createdDate,
			Date modifiedDate, Users users) {
		super();
		this.id = id;
		this.userid = userid;
		this.usersModified = usersModified;
		this.paymentMode = paymentMode;
		this.paymentModeType = paymentModeType;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
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
	 * @return the usersModified
	 */
	public Users getUsersModified() {
		return usersModified;
	}

	/**
	 * @param usersModified
	 *            the usersModified to set
	 */
	public void setUsersModified(Users usersModified) {
		this.usersModified = usersModified;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *            the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentModeType
	 */
	public Short getPaymentModeType() {
		return paymentModeType;
	}

	/**
	 * @param paymentModeType
	 *            the paymentModeType to set
	 */
	public void setPaymentModeType(Short paymentModeType) {
		this.paymentModeType = paymentModeType;
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

}
