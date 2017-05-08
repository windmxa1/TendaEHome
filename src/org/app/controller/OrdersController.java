package org.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bean.MapBean;
import org.dao.OrdersDao;
import org.dao.imp.OrdersDaoImp;
import org.model.Orders;
import org.model.OrdersDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.JsonUtils;
import org.util.ResultUtils;
import org.util.TokenUtils;
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
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		List<VOrdersId> list = oDao.getList(userid, start, limit);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			for (VOrdersId order : list) {
				List<VOrdersDetailsId> details = oDao.getDetailList(
						order.getId(), start, limit);
				order.setList(details);
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
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/test")
	@ResponseBody
	public Object test(HttpServletRequest request, Long addressId) {
		System.out.println(addressId);
		
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Object cancelOrder(HttpServletRequest request, Long id) {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		if (oDao.cancel(userid, id)) {
			return ResultUtils.toJson(100, "取消订单成功", "");
		} else {
			return ResultUtils.toJson(101, "取消订单失败", "");
		}
	}

	@RequestMapping("/addOrder")
	@ResponseBody
	public Object addOrder(HttpServletRequest request,Long addressId,
			String details) {
		System.out.println(request.getAttribute("AddressId"));
		System.out.println(request.getAttribute("details"));
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		System.out.println("userid:"+userid);
		
		/*********************************/
		Orders orders = new Orders(userid, System.currentTimeMillis() / 1000,
				0, addressId);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
				OrdersDetail.class);
		List<OrdersDetail> details2 = null;
		try {
			System.out.println(mapper);
			details2 = mapper.readValue(details, javaType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (oDao.generateOrder(orders, details2)) {
			return ResultUtils.toJson(100, "生成订单成功", "");
		} else {
			return ResultUtils.toJson(101, "生成订单失败", "");
		}
	}
}
