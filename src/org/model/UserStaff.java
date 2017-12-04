package org.model;

/**
 * UserStaff entity. @author MyEclipse Persistence Tools
 */

public class UserStaff implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String passowrd;
	private String staffNo;

	// Constructors

	/** default constructor */
	public UserStaff() {
	}

	/** full constructor */
	public UserStaff(Long id, String username, String passowrd, String staffNo) {
		this.id = id;
		this.username = username;
		this.passowrd = passowrd;
		this.staffNo = staffNo;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassowrd() {
		return this.passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public String getStaffNo() {
		return this.staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

}