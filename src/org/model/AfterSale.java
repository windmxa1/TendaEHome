package org.model;

/**
 * AfterSale entity. @author MyEclipse Persistence Tools
 */

public class AfterSale implements java.io.Serializable {

	// Fields

	private String afterSaleId;
	private Long orderId;
	private String reason;
	private String handleResult;
	private String refundId;
	private Long time;
	private Integer state;

	// Constructors

	/** default constructor */
	public AfterSale() {
	}

	/** minimal constructor */
	public AfterSale(String afterSaleId, Long orderId, String reason, Long time) {
		this.afterSaleId = afterSaleId;
		this.orderId = orderId;
		this.reason = reason;
		this.time = time;
	}

	/** full constructor */
	public AfterSale(String afterSaleId, Long orderId, String reason,
			String handleResult, String refundId, Long time) {
		this.afterSaleId = afterSaleId;
		this.orderId = orderId;
		this.reason = reason;
		this.handleResult = handleResult;
		this.refundId = refundId;
		this.time = time;
	}

	public AfterSale(String afterSaleId, Long orderId, String reason,
			String handleResult, String refundId, Long time, Integer state) {
		super();
		this.afterSaleId = afterSaleId;
		this.orderId = orderId;
		this.reason = reason;
		this.handleResult = handleResult;
		this.refundId = refundId;
		this.time = time;
		this.state = state;
	}

	// Property accessors

	public String getAfterSaleId() {
		return this.afterSaleId;
	}

	public void setAfterSaleId(String afterSaleId) {
		this.afterSaleId = afterSaleId;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}