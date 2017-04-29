package org.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class OrdersController {
	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList() {
		
		return null;
	}

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Object cancelOrder() {

		return null;
	}

	@RequestMapping("/addOrder")
	@ResponseBody
	public Object addOrder() {

		return null;

	}
}
