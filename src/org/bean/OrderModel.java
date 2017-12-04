package org.bean;

import java.util.ArrayList;
import java.util.List;

import org.model.OrdersDetail;

public class OrderModel {
	private List<OrdersDetail> details = new ArrayList<>();
	private Long addressId;
	private Double total;
	private Integer type=0;
	private Long franchiseeId=0L ;
	private String remarks = "";
//	private Integer payWay;

//	public Integer getPayWay() {
//		return payWay;
//	}
//
//	public void setPayWay(Integer payWay) {
//		this.payWay = payWay;
//	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getFranchiseeId() {
		return franchiseeId;
	}

	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<OrdersDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrdersDetail> details) {
		this.details = details;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
