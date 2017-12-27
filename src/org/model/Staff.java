package org.model;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private Integer id;
	private String staffNo;
	private String staffName;
	private Integer year;
	private String serviceVange;
	private String username;
	private String password;
	private Integer isLeader;
	private String distance;

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(String staffNo) {
		this.staffNo = staffNo;
	}

	public Staff(String staffNo, String username, String password) {
		super();
		this.staffNo = staffNo;
		this.username = username;
		this.password = password;
	}

	public Staff(String staffNo, String username, String password,
			Integer isLeader) {
		super();
		this.staffNo = staffNo;
		this.username = username;
		this.password = password;
		this.isLeader = isLeader;
	}

	/** full constructor */
	public Staff(String staffNo, String staffName, Integer year,
			String serviceVange, String username, String password,
			Integer isLeader) {
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.year = year;
		this.serviceVange = serviceVange;
		this.username = username;
		this.password = password;
		this.isLeader = isLeader;
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

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getServiceVange() {
		return this.serviceVange;
	}

	public void setServiceVange(String serviceVange) {
		this.serviceVange = serviceVange;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsLeader() {
		return this.isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}