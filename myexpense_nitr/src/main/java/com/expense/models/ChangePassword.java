/**
 * 
 */
package com.expense.models;

import java.io.Serializable;

/**
 * @author j.saini
 * 
 */
public class ChangePassword implements Serializable {

	private static final long serialVersionUID = 2074920539067993833L;

	private String password;
	private String newpassword;
	private String retypenewpassword;

	private String userid;

	/**
	 * @return the newpassword
	 */

	public String getNewpassword() {
		return newpassword;
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
	 * @param newpassword
	 *            the newpassword to set
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	/**
	 * @return the retypenewpassword
	 */

	public String getRetypenewpassword() {
		return retypenewpassword;
	}

	/**
	 * @param retypenewpassword
	 *            the retypenewpassword to set
	 */
	public void setRetypenewpassword(String retypenewpassword) {
		this.retypenewpassword = retypenewpassword;
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

}
