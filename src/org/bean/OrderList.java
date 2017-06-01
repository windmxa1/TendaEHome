package org.bean;

import java.util.ArrayList;
import java.util.List;

import org.model.OrdersDetail;


public class OrderList {
	private List<OrdersDetail> details = new ArrayList<>();

	public List<OrdersDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrdersDetail> details) {
		this.details = details;
	}

}
