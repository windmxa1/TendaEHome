package org.view;

import java.util.List;

/**
 * VOrdersId entity. @author MyEclipse Persistence Tools
 */

public class VOrdersId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long time;
	private Double total;
	private Long num;
	private String createTime;
	private Integer state;
	private String status;
	private String address;
	private String orderNum;
	private List<VOrdersDetailsId> details;
	private List<String> urlList;

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
	public VOrdersId(Long id, Long userid, Long time, Double total, Long num,
			String createTime, Integer state, String status, String address,
			String orderNum) {
		this.id = id;
		this.userid = userid;
		this.time = time;
		this.total = total;
		this.num = num;
		this.createTime = createTime;
		this.state = state;
		this.status = status;
		this.address = address;
		this.orderNum = orderNum;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VOrdersId))
			return false;
		VOrdersId castOther = (VOrdersId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getTotal() == castOther.getTotal()) || (this
						.getTotal() != null && castOther.getTotal() != null && this
						.getTotal().equals(castOther.getTotal())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null
						&& castOther.getNum() != null && this.getNum().equals(
						castOther.getNum())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getOrderNum() == castOther.getOrderNum()) || (this
						.getOrderNum() != null
						&& castOther.getOrderNum() != null && this
						.getOrderNum().equals(castOther.getOrderNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getTotal() == null ? 0 : this.getTotal().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getOrderNum() == null ? 0 : this.getOrderNum().hashCode());
		return result;
	}

}