package org.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.OrdersDao;
import org.dao.imp.OrdersDaoImp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.Orders;
import org.model.OrdersDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.HibernateSessionFactory;
import org.util.ResultUtils;
import org.view.VOrdersId;

@Controller
public class OrdersController {
	OrdersDao oDao;
	Long userid;
	Map<String, Object> data;

	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(Integer start, Integer limit) {
		oDao = new OrdersDaoImp();
		List<VOrdersId> list = oDao.getList(userid, start, limit);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<VOrdersId>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/test")
	@ResponseBody
	public Object test(HttpServletRequest request) {
		return null;
	}

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Object cancelOrder(Long id) {
		oDao = new OrdersDaoImp();
		if (oDao.cancel(userid, id)) {
			return ResultUtils.toJson(100, "取消订单成功", "");
		} else {
			return ResultUtils.toJson(101, "取消订单失败", "");
		}
	}

	@RequestMapping("/addOrder")
	@ResponseBody
	public Object addOrder(Long AddressId,
			@RequestBody List<OrdersDetail> details) {
		oDao = new OrdersDaoImp();
		Orders orders = new Orders(userid, System.currentTimeMillis() / 1000,
				0, AddressId);
		if (oDao.generateOrder(orders, details)) {
			return ResultUtils.toJson(100, "生成订单成功", "");
		} else {
			return ResultUtils.toJson(101, "生成订单失败", "");
		}
	}
}
