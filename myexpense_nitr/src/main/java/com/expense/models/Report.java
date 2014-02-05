/**
 * 
 */
package com.expense.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jitender
 * 
 */
public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4620877216472804929L;

	private Date from;

	private Date to;

	private short grouping;

	/**
	 * @return the from
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(Date to) {
		this.to = to;
	}

	/**
	 * @return the grouping
	 */
	public short getGrouping() {
		return grouping;
	}

	/**
	 * @param grouping
	 *            the grouping to set
	 */
	public void setGrouping(short grouping) {
		this.grouping = grouping;
	}

}
