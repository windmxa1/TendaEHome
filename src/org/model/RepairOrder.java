package org.model;

/**
 * RepairOrder entity. @author MyEclipse Persistence Tools
 */

public class RepairOrder implements java.io.Serializable {

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

	// Constructors

	/** default constructor */
	public RepairOrder() {
	}

	/** minimal constructor */
	public RepairOrder(String appointmentTime, String address,
			String description, String phone, Long userid) {
		this.appointmentTime = appointmentTime;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.userid = userid;
	}

	/** full constructor */
	public RepairOrder(String appointmentTime, String address,
			String description, String phone, Integer status,
			String handleResult, Long userid, String staffId) {
		this.appointmentTime = appointmentTime;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.status = status;
		this.handleResult = handleResult;
		this.userid = userid;
		this.staffId = staffId;
	}

	public RepairOrder(Long id, String appointmentTime, String address,
			String description, String phone, Integer status,
			String handleResult, Long userid, String staffId) {
		this.id = id;
		this.appointmentTime = appointmentTime;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.status = status;
		this.handleResult = handleResult;
		this.userid = userid;
		this.staffId = staffId;
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

}