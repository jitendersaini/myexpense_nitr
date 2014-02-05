/**
 * 
 */
package com.expense.models;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author jitender
 * 
 */
public class ReportData implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 4620877216472804929L;

	private String title;
	private Double value;
	private String paidVia;
	private String paidViaType;
	private String category;
	private String expenseDate;
	private String createdBy;
	private String modifiedBy;
	private String createdDate;
	private String modifiedDate;
	private Integer year;
	private String month;
	private Date expenseDateSort;
	private Date createdDateSort;
	private Date modifiedDateSort;

	/**
	 * @return the createdDateSort
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreatedDateSort() {
		return createdDateSort;
	}

	/**
	 * @param createdDateSort
	 *            the createdDateSort to set
	 */
	public void setCreatedDateSort(Date createdDateSort) {
		this.createdDateSort = createdDateSort;
	}

	/**
	 * @return the modifiedDateSort
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getModifiedDateSort() {
		return modifiedDateSort;
	}

	/**
	 * @param modifiedDateSort
	 *            the modifiedDateSort to set
	 */
	public void setModifiedDateSort(Date modifiedDateSort) {
		this.modifiedDateSort = modifiedDateSort;
	}

	/**
	 * @return the expenseDateSort
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getExpenseDateSort() {
		return expenseDateSort;
	}

	/**
	 * @param expenseDateSort
	 *            the expenseDateSort to set
	 */
	public void setExpenseDateSort(Date expenseDateSort) {
		this.expenseDateSort = expenseDateSort;
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
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the expenseDate
	 */
	public String getExpenseDate() {
		return expenseDate;
	}

	/**
	 * @param expenseDate
	 *            the expenseDate to set
	 */
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return
	 */
	public String getPaidVia() {
		return paidVia;
	}

	/**
	 * @param paidVia
	 */
	public void setPaidVia(String paidVia) {
		this.paidVia = paidVia;
	}

	/**
	 * @return
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the paidViaType
	 */
	public String getPaidViaType() {
		return paidViaType;
	}

	/**
	 * @param paidViaType
	 *            the paidViaType to set
	 */
	public void setPaidViaType(String paidViaType) {
		this.paidViaType = paidViaType;
	}

}
