package org.model;

/**
 * AfterSale entity. @author MyEclipse Persistence Tools
 */

public class AfterSale implements java.io.Serializable {

	// Fields

	private String aftersaleId;
	private Long orderId;
	private String reason;
	private String handleResult;
	private String refundId;
	private Long time;

	// Constructors

	/** default constructor */
	public AfterSale() {
	}

	/** minimal constructor */
	public AfterSale(String aftersaleId, Long orderId, String reason, Long time) {
		this.aftersaleId = aftersaleId;
		this.orderId = orderId;
		this.reason = reason;
		this.time = time;
	}

	/** full constructor */
	public AfterSale(String aftersaleId, Long orderId, String reason,
			String handleResult, String refundId, Long time) {
		this.aftersaleId = aftersaleId;
		this.orderId = orderId;
		this.reason = reason;
		this.handleResult = handleResult;
		this.refundId = refundId;
		this.time = time;
	}

	// Property accessors

	public String getAftersaleId() {
		return this.aftersaleId;
	}

	public void setAftersaleId(String aftersaleId) {
		this.aftersaleId = aftersaleId;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getHandleResult() {
		return this.handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getRefundId() {
		return this.refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}