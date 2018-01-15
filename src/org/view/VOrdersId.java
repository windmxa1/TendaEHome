package org.view;

import java.util.List;

import org.model.AfterSale;

/**
 * VOrdersId entity. @author MyEclipse Persistence Tools
 */

public class VOrdersId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long time;
	private Integer isExport;
	private Integer payWay;
	private Integer deliveryState;
	private String refundId;
	private Integer afterSaleState;
	private Long franchiseeId;
	private Integer type;
	private String remarks;
	private Double total;
	private Long num;
	private String franchiseeName;
	private String staffName;
	private String createTime;
	private Integer state;
	private String status;
	private String address;
	private String orderNum;
	private List<VOrdersDetailsId> details;
	private List<String> urlList;
	private AfterSale afterSale;
	private Integer isComment;

	// Constructors

	/** default constructor */
	public VOrdersId() {
	}

	/** minimal constructor */
	public VOrdersId(Long id, Long userid, Long time, String address,
			String orderNum) {
		this.id = id;
		this.userid = userid;
		this.time = time;
		this.address = address;
		this.orderNum = orderNum;
	}

	/** full constructor */
	public VOrdersId(Long id, Long userid, Long time, Integer isExport,
			Integer payWay, Integer deliveryState, String refundId,
			Integer afterSaleState, Long franchiseeId, Integer type,
			String remarks, String address, String orderNum, Integer isComment,
			Double total, Long num, String franchiseeName, String staffName,
			String createTime, Integer state, String status) {
		this.id = id;
		this.userid = userid;
		this.time = time;
		this.isExport = isExport;
		this.payWay = payWay;
		this.deliveryState = deliveryState;
		this.refundId = refundId;
		this.afterSaleState = afterSaleState;
		this.franchiseeId = franchiseeId;
		this.type = type;
		this.remarks = remarks;
		this.address = address;
		this.orderNum = orderNum;
		this.isComment = isComment;
		this.total = total;
		this.num = num;
		this.franchiseeName = franchiseeName;
		this.staffName = staffName;
		this.createTime = createTime;
		this.state = state;
		this.status = status;
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

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getIsExport() {
		return this.isExport;
	}

	public void setIsExport(Integer isExport) {
		this.isExport = isExport;
	}

	public Integer getPayWay() {
		return this.payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public Integer getDeliveryState() {
		return this.deliveryState;
	}

	public void setDeliveryState(Integer deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getRefundId() {
		return this.refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
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

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getFranchiseeName() {
		return this.franchiseeName;
	}

	public void setFranchiseeName(String franchiseeName) {
		this.franchiseeName = franchiseeName;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public List<VOrdersDetailsId> getDetails() {
		return details;
	}

	public void setDetails(List<VOrdersDetailsId> details) {
		this.details = details;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public AfterSale getAfterSale() {
		return afterSale;
	}

	public void setAfterSale(AfterSale afterSale) {
		this.afterSale = afterSale;
	}

	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getIsExport() == null ? 0 : this.getIsExport().hashCode());
		result = 37 * result
				+ (getPayWay() == null ? 0 : this.getPayWay().hashCode());
		result = 37
				* result
				+ (getDeliveryState() == null ? 0 : this.getDeliveryState()
						.hashCode());
		result = 37 * result
				+ (getRefundId() == null ? 0 : this.getRefundId().hashCode());
		result = 37
				* result
				+ (getAfterSaleState() == null ? 0 : this.getAfterSaleState()
						.hashCode());
		result = 37
				* result
				+ (getFranchiseeId() == null ? 0 : this.getFranchiseeId()
						.hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getRemarks() == null ? 0 : this.getRemarks().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getOrderNum() == null ? 0 : this.getOrderNum().hashCode());
		result = 37 * result
				+ (getIsComment() == null ? 0 : this.getIsComment().hashCode());
		result = 37 * result
				+ (getTotal() == null ? 0 : this.getTotal().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37
				* result
				+ (getFranchiseeName() == null ? 0 : this.getFranchiseeName()
						.hashCode());
		result = 37 * result
				+ (getStaffName() == null ? 0 : this.getStaffName().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}
}