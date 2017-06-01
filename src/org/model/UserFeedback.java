package org.model;

/**
 * UserFeedback entity. @author MyEclipse Persistence Tools
 */

public class UserFeedback implements java.io.Serializable {

	// Fields

	private Integer id;
	private String message;
	private Long userId;
	private Long time;
	private Short read;

	// Constructors

	/** default constructor */
	public UserFeedback() {
	}

	public UserFeedback(String message, Long userId, Long time) {
		this.message = message;
		this.userId = userId;
		this.time = time;
	}

	/** full constructor */
	public UserFeedback(String message, Long userId, Long time, Short read) {
		this.message = message;
		this.userId = userId;
		this.time = time;
		this.read = read;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Short getRead() {
		return this.read;
	}

	public void setRead(Short read) {
		this.read = read;
	}

}