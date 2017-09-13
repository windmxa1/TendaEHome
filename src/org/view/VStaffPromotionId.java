package org.view;

import java.sql.Timestamp;

/**
 * VStaffPromotionId entity. @author MyEclipse Persistence Tools
 */

public class VStaffPromotionId implements java.io.Serializable {

	// Fields

	private Long id;
	private String address;
	private String staffNo;
	private Timestamp time;
	private String staffName;

	// Constructors

	/** default constructor */
	public VStaffPromotionId() {
	}

	/** minimal constructor */
	public VStaffPromotionId(Long id, String address, String staffNo) {
		this.id = id;
		this.address = address;
		this.staffNo = staffNo;
	}

	/** full constructor */
	public VStaffPromotionId(Long id, String address, String staffNo,
			Timestamp time, String staffName) {
		this.id = id;
		this.address = address;
		this.staffNo = staffNo;
		this.time = time;
		this.staffName = staffName;
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

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VStaffPromotionId))
			return false;
		VStaffPromotionId castOther = (VStaffPromotionId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getStaffNo() == castOther.getStaffNo()) || (this
						.getStaffNo() != null && castOther.getStaffNo() != null && this
						.getStaffNo().equals(castOther.getStaffNo())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getStaffName() == castOther.getStaffName()) || (this
						.getStaffName() != null
						&& castOther.getStaffName() != null && this
						.getStaffName().equals(castOther.getStaffName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getStaffNo() == null ? 0 : this.getStaffNo().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getStaffName() == null ? 0 : this.getStaffName().hashCode());
		return result;
	}

}