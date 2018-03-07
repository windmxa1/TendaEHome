package org.bean;

import java.util.List;

import org.model.OrdersDetail;

public class CartBean {
	private List<OrdersDetail> list;
	private Integer actId;
	private String name;
	private Double minPrice;
	// private Integer type;
	private OrdersDetail gift;

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	// public Integer getType() {
	// return type;
	// }
	//
	// public void setType(Integer type) {
	// this.type = type;
	// }

	public OrdersDetail getGift() {
		return gift;
	}

	public void setGift(OrdersDetail gift) {
		this.gift = gift;
	}

	public List<OrdersDetail> getList() {
		return list;
	}

	public void setList(List<OrdersDetail> list) {
		this.list = list;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
