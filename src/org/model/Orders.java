package org.model;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderNum;
	private Long userid;
	private Long time;
	private String address;
	private String staffNo;
	private Integer isExport;
	private Integer deliveryState;
	private Integer payWay;
	private String refundId;
	private Long finishTime;
	private Integer afterSaleState;
	private Long franchiseeId;
	private Integer type;
	private String remarks;

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(String orderNum, Long userid, Long time, String address) {
		this.orderNum = orderNum;
		this.userid = userid;
		this.time = time;
		this.address = address;
	}

	/** full constructor */
	public Orders(String orderNum, Long userid, Long time, String address,
			String staffNo, Integer isExport, Integer deliveryState,
			Integer payWay, String refundId, Long finishTime,
			Integer afterSaleState, Long franchiseeId, Integer type,
			String remarks) {
		this.orderNum = orderNum;
		this.userid = userid;
		this.time = time;
		this.address = address;
		this.staffNo = staffNo;
		this.isExport = isExport;
		this.deliveryState = deliveryState;
		this.payWay = payWay;
		this.refundId = refundId;
		this.finishTime = finishTime;
		this.afterSaleState = afterSaleState;
		this.franchiseeId = franchiseeId;
		this.type = type;
		this.remarks = remarks;
	}

	// Property accessors

	public Orders(Long userid, Long time, String address, String orderNum,
			Long franchiseeId, Integer type,String remarks) {
		super();
		this.orderNum = orderNum;
		this.userid = userid;
		this.time = time;
		this.address = address;
		this.franchiseeId = franchiseeId;
		this.type = type;
		this.remarks = remarks;
	}

	public Orders(Long userid, Long time, String address, String orderNum) {
		super();
		this.orderNum = orderNum;
		this.userid = userid;
		this.time = time;
		this.address = address;
	}

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

	public String getstaffNo() {
		return this.staffNo;
	}

	public void setstaffNo(String staffNo) {
		this.staffNo = staffNo;
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

	public Long getFranchiseeId() {
		return this.franchiseeId;
	}

	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}