package org.model;

import java.sql.Timestamp;

/**
 * StaffPromotion entity. @author MyEclipse Persistence Tools
 */

public class StaffPromotion implements java.io.Serializable {

	// Fields

	private Long id;
	private String address;
	private String staffNo;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public StaffPromotion() {
	}

	/** minimal constructor */
	public StaffPromotion(String address, String staffNo) {
		this.address = address;
		this.staffNo = staffNo;
	}

	/** full constructor */
	public StaffPromotion(String address, String staffNo, Timestamp time) {
		this.address = address;
		this.staffNo = staffNo;
		this.time = time;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStaffNo() {
		return this.staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}