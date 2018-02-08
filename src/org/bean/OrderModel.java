package org.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.model.OrdersDetail;

public class OrderModel {
	// private List<OrdersDetail> details = new ArrayList<>();
	private Map<String, List<OrdersDetail>> details = new HashMap<>();
	private Long addressId;
	private Double total;
	private Integer type = 0;
	private Long franchiseeId = 0L;
	private String remarks = "";
	private List<String> selectIds = new ArrayList<>();

	// private Integer payWay;

	// public Integer getPayWay() {
	// return payWay;
	// }
	//
	// public void setPayWay(Integer payWay) {
	// this.payWay = payWay;
	// }

	public String getRemarks() {
		return remarks;
	}

	public List<String> getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(List<String> selectIds) {
		this.selectIds = selectIds;
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

	public Map<String, List<OrdersDetail>> getDetails() {
		return details;
	}

	public void setDetails(Map<String, List<OrdersDetail>> details) {
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
