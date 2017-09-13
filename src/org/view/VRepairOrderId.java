package org.view;

/**
 * VRepairOrderId entity. @author MyEclipse Persistence Tools
 */

public class VRepairOrderId implements java.io.Serializable {

	// Fields

	private Long id;
	private String appointmentTime;
	private String address;
	private String description;
	private String phone;
	private Integer status;
	private String handleResult;
	private Long userid;
	private String staffId;
	private String state;

	// Constructors

	/** default constructor */
	public VRepairOrderId() {
	}

	/** minimal constructor */
	public VRepairOrderId(Long id, String appointmentTime, String address,
			String description, String phone, Long userid) {
		this.id = id;
		this.appointmentTime = appointmentTime;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.userid = userid;
	}

	/** full constructor */
	public VRepairOrderId(Long id, String appointmentTime, String address,
			String description, String phone, Integer status,
			String handleResult, Long userid, String staffId, String state) {
		this.id = id;
		this.appointmentTime = appointmentTime;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.status = status;
		this.handleResult = handleResult;
		this.userid = userid;
		this.staffId = staffId;
		this.state = state;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppointmentTime() {
		return this.appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHandleResult() {
		return this.handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VRepairOrderId))
			return false;
		VRepairOrderId castOther = (VRepairOrderId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getAppointmentTime() == castOther
						.getAppointmentTime()) || (this.getAppointmentTime() != null
						&& castOther.getAppointmentTime() != null && this
						.getAppointmentTime().equals(
								castOther.getAppointmentTime())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getHandleResult() == castOther.getHandleResult()) || (this
						.getHandleResult() != null
						&& castOther.getHandleResult() != null && this
						.getHandleResult().equals(castOther.getHandleResult())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getStaffId() == castOther.getStaffId()) || (this
						.getStaffId() != null && castOther.getStaffId() != null && this
						.getStaffId().equals(castOther.getStaffId())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getAppointmentTime() == null ? 0 : this.getAppointmentTime()
						.hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getHandleResult() == null ? 0 : this.getHandleResult()
						.hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getStaffId() == null ? 0 : this.getStaffId().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		return result;
	}

}