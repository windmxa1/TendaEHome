package org.model;

/**
 * DishOrders entity. @author MyEclipse Persistence Tools
 */

public class DishOrders implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderNum;
	private Long userid;
	private Long franchiseeId;
	private Long time;
	private String address;
	private String staffId;
	private Integer isExport;
	private Integer deliveryState;
	private Integer payWay;
	private String refundId;
	private Long finishTime;
	private Integer afterSaleState;

	// Constructors

	/** default constructor */
	public DishOrders() {
	}

	/** minimal constructor */
	public DishOrders(String orderNum, Long userid, Long time, String address) {
		this.orderNum = orderNum;
		this.userid = userid;
		this.time = time;
		this.address = address;
	}

	/** full constructor */
	public DishOrders(String orderNum, Long userid, Long franchiseeId,
			Long time, String address, String staffId, Integer isExport,
			Integer deliveryState, Integer payWay, String refundId,
			Long finishTime, Integer afterSaleState) {
		this.orderNum = orderNum;
		this.userid = userid;
		this.franchiseeId = franchiseeId;
		this.time = time;
		this.address = address;
		this.staffId = staffId;
		this.isExport = isExport;
		this.deliveryState = deliveryState;
		this.payWay = payWay;
		this.refundId = refundId;
		this.finishTime = finishTime;
		this.afterSaleState = afterSaleState;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getFranchiseeId() {
		return this.franchiseeId;
	}

	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Integer getIsExport() {
		return this.isExport;
	}

	public void setIsExport(Integer isExport) {
		this.isExport = isExport;
	}

	public Integer getDeliveryState() {
		return this.deliveryState;
	}

	public void setDeliveryState(Integer deliveryState) {
		this.deliveryState = deliveryState;
	}

	public Integer getPayWay() {
		return this.payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public String getRefundId() {
		return this.refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public Long getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getAfterSaleState() {
		return this.afterSaleState;
	}

	public void setAfterSaleState(Integer afterSaleState) {
		this.afterSaleState = afterSaleState;
	}

}