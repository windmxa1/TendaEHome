package org.model;

/**
 * Refund entity. @author MyEclipse Persistence Tools
 */

public class Refund implements java.io.Serializable {

	// Fields

	private String refundId;
	private Long orderId;
	private Double refundFee;
	private Integer state;
	private String description;
	private Long time;

	// Constructors

	/** default constructor */
	public Refund() {
	}

	/** minimal constructor */
	public Refund(String refundId, Long orderId, Double refundFee) {
		this.refundId = refundId;
		this.orderId = orderId;
		this.refundFee = refundFee;
	}

	public Refund(String refundId, Long orderId, Double refundFee,
			 String description, Long time) {
		this.refundId = refundId;
		this.orderId = orderId;
		this.refundFee = refundFee;
		this.state = state;
		this.description = description;
		this.time = time;
	}
	/** full constructor */
	public Refund(String refundId, Long orderId, Double refundFee,
			Integer state, String description, Long time) {
		this.refundId = refundId;
		this.orderId = orderId;
		this.refundFee = refundFee;
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

	public Double getRefundFee() {
		return this.refundFee;
	}

	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
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