package org.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.OrdersDao;
import org.dao.imp.OrdersDaoImp;
import org.model.Orders;
import org.model.OrdersDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.JsonUtils;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.util.Utils;
import org.util.WXAPI;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("/app/OrdersController")
@RequestMapping("/app/orders")
public class OrdersController {
	OrdersDao oDao;
	// Long userid;
	Map<String, Object> data;

	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request, Integer start,
			Integer limit) {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		List<VOrdersId> list = oDao.getList(userid, start, limit);
		data = new HashMap<String, Object>();
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			for (VOrdersId order : list) {
				List<VOrdersDetailsId> details = oDao.getDetailList(
						order.getId(), 0, -1);
				order.setDetails(details);
			}
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrdersDetailList")
	@ResponseBody
	public Object getOrdersDetailList(Long orderId, Integer start, Integer limit) {
		oDao = new OrdersDaoImp();
		List<VOrdersDetailsId> list = oDao.getDetailList(orderId, start, limit);
		data = new HashMap<>();
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrders")
	@ResponseBody
	public Object getOrders(HttpServletRequest request, Integer start,
			Integer limit) {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		System.out.println(userid);
		List<VOrdersId> list = oDao.getList(userid, start, limit);
		data = new HashMap<String, Object>();
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public Object test(HttpServletRequest request,
			@RequestParam Long addressId, @RequestParam String details) {
		System.out.println(addressId);
		System.out.println(details);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping(value = "/test1", method = RequestMethod.POST)
	@ResponseBody
	public Object test1(HttpServletRequest request,
			@RequestParam Object addressId) {
		System.out.println(addressId);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Object cancelOrder(HttpServletRequest request, Long id) {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (oDao.cancel(userid, id)) {
			return ResultUtils.toJson(100, "取消订单成功", "");
		} else {
			return ResultUtils.toJson(101, "取消订单失败", "");
		}
	}

	@RequestMapping("/addOrder")
	@ResponseBody
	public Object addOrder(HttpServletRequest request, Long addressId,
			String details) {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		System.out.println("userid:" + userid);
		Long time = System.currentTimeMillis();
		String orderNum = time + Utils.ran6();
		/*********************************/
		Orders orders = new Orders(userid, time / 1000, 0, addressId, orderNum);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
				OrdersDetail.class);
		List<OrdersDetail> details2 = null;
		try {
			details2 = mapper.readValue(details, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (oDao.generateOrder(orders, details2)) {
			String clientIp = request.getRemoteAddr();
			// 订单总价不能包含小数，单位为分，因此乘100并转整型
			// 不使用APP端传递的总价是为了防止数据被恶意修改导致无法匹配
			Double total = oDao.getTotal(orderNum);
			Integer fee = (int) (total * 100);
			data = WXAPI.doPay(orderNum, fee, clientIp);
			if (data == null) {
				System.out.println("data==null");
				return ResultUtils.toJson(101, "生成订单失败，请重试", "");
			}
			// data.put("orderNum", orderNum);
			return ResultUtils.toJson(100, "生成订单成功", data);
		} else {
			return ResultUtils.toJson(101, "生成订单失败，请重试", "");
		}
	}

}
