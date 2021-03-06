package org.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Long id;
	private String phone;
	private String password;
	private Long time;
	private String nickname;
	private String headUrl;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String phone, String password, Long time) {
		this.phone = phone;
		this.password = password;
		this.time = time;
	}

	/** full constructor */
	public User(String phone, String password, Long time, String nickname,
			String headUrl) {
		this.phone = phone;
		this.password = password;
		this.time = time;
		this.nickname = nickname;
		this.headUrl = headUrl;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadUrl() {
		return this.headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

}