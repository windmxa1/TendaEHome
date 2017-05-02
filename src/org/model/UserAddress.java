package org.model;

/**
 * UserAddress entity. @author MyEclipse Persistence Tools
 */

public class UserAddress implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String address;
	private String recevier;

	// Constructors

	/** default constructor */
	public UserAddress() {
	}

	/** full constructor */
	public UserAddress(Long userid, String address, String recevier) {
		this.userid = userid;
		this.address = address;
		this.recevier = recevier;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRecevier() {
		return this.recevier;
	}

	public void setRecevier(String recevier) {
		this.recevier = recevier;
	}

}