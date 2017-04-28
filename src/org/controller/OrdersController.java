package org.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class OrdersController {
	@RequestMapping("/getOrdersList")
	public @ResponseBody
	Object getOrdersList(){
		return null;
	}
	@RequestMapping("/cancelOrder")
	public @ResponseBody
	Object cancelOrder(){
		
		return null;
	}
	@RequestMapping("/addOrder")
	public @ResponseBody
	Object addOrder(){
		
		return null;
		
	}
}
