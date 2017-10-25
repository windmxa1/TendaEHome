package org.model;

/**
 * Refund entity. @author MyEclipse Persistence Tools
 */

public class Refund implements java.io.Serializable {

	// Fields

	private String refundId;
	private Long orderId;
	private Integer state;
	private String description;
	private Long time;

	// Constructors

	/** default constructor */
	public Refund() {
	}


	public Refund(String refundId, Long orderId, String description, Long time) {
		super();
		this.refundId = refundId;
		this.orderId = orderId;
		this.description = description;
		this.time = time;
	}


	/** minimal constructor */
	public Refund(String refundId, Long orderId) {
		this.refundId = refundId;
		this.orderId = orderId;
	}

	/** full constructor */
	public Refund(String refundId, Long orderId, Integer state,
			String description, Long time) {
		this.refundId = refundId;
		this.orderId = orderId;
		this.state = state;
		this.description = description;
		this.time = time;
	}

	// Property accessors

	public String getRefundId() {
		return this.refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}