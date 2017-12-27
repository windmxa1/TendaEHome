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
	private Integer staffId;
	private Integer isRead;
	private Integer isStaffRead;
	private String lat;
	private String lon;

	// Constructors

	/** default constructor */
	public RepairOrder() {
	}

	/** minimal constructor */
	public RepairOrder(String appointmentTime, String address,
			String description, String phone, Long userid,String lat,String lon) {
		this.appointmentTime = appointmentTime;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.userid = userid;
		this.lat = lat;
		this.lon = lon;
	}
	

	/** full constructor */
	public RepairOrder(String appointmentTime, String address,
			String description, String phone, Integer status,
			String handleResult, Long userid, Integer staffId, Integer isRead,
			Integer isStaffRead, String lat, String lon) {
		this.appointmentTime = appointmentTime;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.status = status;
		this.handleResult = handleResult;
		this.userid = userid;
		this.staffId = staffId;
		this.isRead = isRead;
		this.isStaffRead = isStaffRead;
		this.lat = lat;
		this.lon = lon;
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

	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getIsStaffRead() {
		return this.isStaffRead;
	}

	public void setIsStaffRead(Integer isStaffRead) {
		this.isStaffRead = isStaffRead;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

}