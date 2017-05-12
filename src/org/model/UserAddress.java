package org.model;

/**
 * UserAddress entity. @author MyEclipse Persistence Tools
 */

public class UserAddress implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String address;
	private String receiver;
	private Short default_;
	private String tel;
	private String sex;

	// Constructors

	/** default constructor */
	public UserAddress() {
	}

	/** minimal constructor */
	public UserAddress(Long userid, String address, String receiver,
			String tel, String sex) {
		this.userid = userid;
		this.address = address;
		this.receiver = receiver;
		this.tel = tel;
		this.sex = sex;
	}

	/** full constructor */
	public UserAddress(Long userid, String address, String receiver,
			Short default_, String tel, String sex) {
		this.userid = userid;
		this.address = address;
		this.receiver = receiver;
		this.default_ = default_;
		this.tel = tel;
		this.sex = sex;
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

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Short getDefault_() {
		return this.default_;
	}

	public void setDefault_(Short default_) {
		this.default_ = default_;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}