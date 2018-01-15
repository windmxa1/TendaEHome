package org.model;

import java.sql.Timestamp;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Long id;
	private String description;
	private Integer rating;
	private Long userid;
	private Long orderId;
	private Integer isVisable;
	private Timestamp time;
	private String reply;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(Long userid, Long orderId) {
		this.userid = userid;
		this.orderId = orderId;
	}

	/** full constructor */
	public Comment(String description, Integer rating, Long userid,
			Long orderId, Integer isVisable, Timestamp time, String reply) {
		this.description = description;
		this.rating = rating;
		this.userid = userid;
		this.orderId = orderId;
		this.isVisable = isVisable;
		this.time = time;
		this.reply = reply;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getIsVisable() {
		return this.isVisable;
	}

	public void setIsVisable(Integer isVisable) {
		this.isVisable = isVisable;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getReply() {
		return this.reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

}