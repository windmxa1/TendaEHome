package org.model;

import java.math.BigDecimal;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staffNo;
	private String staffName;
	private String description;
	private BigDecimal year;
	private String serviceVange;

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** full constructor */
	public Staff(String staffNo, String staffName, String description,
			BigDecimal year, String serviceVange) {
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.description = description;
		this.year = year;
		this.serviceVange = serviceVange;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaffNo() {
		return this.staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getYear() {
		return this.year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public String getServiceVange() {
		return this.serviceVange;
	}

	public void setServiceVange(String serviceVange) {
		this.serviceVange = serviceVange;
	}

}