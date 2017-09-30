package org.app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bean.OrderModel;
import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.UserAddressDao;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserAddressDaoImp;
import org.model.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.util.Utils;
import org.util.WXAPI;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

@Controller("/app/OrdersController")
@RequestMapping("/app/orders")
public class OrdersController {
	OrdersDao oDao;
	GoodsDao gDao;
	UserAddressDao uAddressDao;
	// Long userid;
	Map<String, Object> data;

	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request, Integer start,
			Integer limit) throws Exception {
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
				order.setUrlList(oDao.getUrlList(order.getId()));
			}
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrdersDetailList")
	@ResponseBody
	public Object getOrdersDetailList(Long orderId, Integer start, Integer limit)
			throws Exception {
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
			Integer limit) throws Exception {
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
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/cancelOrder")
	@ResponseBody
	public Object cancelOrder(HttpServletRequest request, Long id)
			throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		switch (oDao.cancel(userid, id)) {
		case 0:
			return ResultUtils.toJson(100, "取消订单成功", "");
		case -1:
			return ResultUtils.toJson(101, "取消订单失败", "");
		case -2:
			return ResultUtils.toJson(101, "该订单在当天9点后无法取消", "");
		default:
			return ResultUtils.toJson(101, "取消订单失败", "");
		}
	}

	@RequestMapping("/deleteOrder")
	@ResponseBody
	public Object deleteOrder(Long id) throws Exception {
		oDao = new OrdersDaoImp();
		if (oDao.deleteOrder(id) > 0) {
			return ResultUtils.toJson(100, "", "");
		}
		return ResultUtils.toJson(101, "您不能删除正在进行的订单，如需请联系客服人员", "");
	}

	@RequestMapping("/addOrder")
	@ResponseBody
	public Object addOrder(HttpServletRequest request, @RequestBody OrderModel o)
			throws Exception {
		oDao = new OrdersDaoImp();
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		Long time = System.currentTimeMillis();
		String orderNum = time + Utils.ran6();
		/*********************************/
		Orders orders = new Orders(userid, time / 1000, 1,
				uAddressDao.getAddressById(o.getAddressId()), orderNum);
		System.out.println(o.getAddressId() + "|||" + orders.getAddress());
		Long id = oDao.generateOrder(orders, o.getDetails());
		if (id > 0) {
			Double Realtotal = oDao.getTotal(orderNum);
			System.out.println(Realtotal);
			if (!("" + Realtotal).equals("" + o.getTotal())) {
				oDao.delOrder(id);
				return ResultUtils.toJson(101, "商品价格与实际价格不符", "");
			}
			data = new HashMap<>();
			data.put("orderNum", orderNum);
			String msg = "生成订单成功";
			if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 21) {
				msg = "生成订单成功，超过21点的订单将于后天送达";
			}
			return ResultUtils.toJson(100, msg, data);
		} else {
			return ResultUtils.toJson(101, "生成订单失败，请重试", "");
		}
	}

	@RequestMapping("/doPay")
	@ResponseBody
	public Object doPay(HttpServletRequest request, String orderNum,
			Integer payWay) throws Exception {
		oDao = new OrdersDaoImp();
		System.out.println(payWay + "" + orderNum);
		Double Realtotal = oDao.getTotal(orderNum);
		switch (payWay) {// 支付方式
		case 0:// 微信
			String clientIp = request.getRemoteAddr();
			// 订单总价不能包含小数，单位为分，因此乘100并转整型
			// 不使用APP端传递的总价是为了防止数据被恶意修改导致无法匹配
			Integer fee = (int) (Realtotal * 100);
			data = WXAPI.doPay(orderNum, fee, clientIp);
			if (data == null) {
				return ResultUtils.toJson(101, "发起支付失败，请重试", "");
			}
			return ResultUtils.toJson(100, "", data);
		case 1:// 支付宝
			return ResultUtils.toJson(101, "目前只支持微信支付", "");
		default:
			return ResultUtils.toJson(101, "目前只支持微信支付", "");
		}
	}
	// @RequestMapping("/addOrder")
	// @ResponseBody
	// public Object addOrder(HttpServletRequest request, @RequestBody
	// OrderModel o)
	// throws Exception {
	// oDao = new OrdersDaoImp();
	// uAddressDao = new UserAddressDaoImp();
	// /**** 获取header中的token并取出userid ****/
	// String token = request.getHeader("token");
	// Long userid = Long.parseLong(""
	// + TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
	// Long time = System.currentTimeMillis();
	// String orderNum = time + Utils.ran6();
	// /*********************************/
	// Orders orders = new Orders(userid, time / 1000, 1,
	// uAddressDao.getAddressById(o.getAddressId()), orderNum);
	// System.out.println(o.getAddressId() + "|||" + orders.getAddress());
	// Long id = oDao.generateOrder(orders, o.getDetails());
	// if (id > 0) {
	// Double Realtotal = oDao.getTotal(orderNum);
	// System.out.println(Realtotal);
	// if (!("" + Realtotal).equals("" + o.getTotal())) {
	// oDao.deleteOrder(id);
	// return ResultUtils.toJson(101, "商品价格与实际价格不符", "");
	// }
	// switch (o.getPayWay()) {
	// case 0:// 微信
	// String clientIp = request.getRemoteAddr();
	// // 订单总价不能包含小数，单位为分，因此乘100并转整型
	// // 不使用APP端传递的总价是为了防止数据被恶意修改导致无法匹配
	// Integer fee = (int) (Realtotal * 100);
	// data = WXAPI.doPay(orderNum, fee, clientIp);
	// if (data == null) {
	// oDao.deleteOrder(id);
	// System.out.println("data==null");
	// return ResultUtils.toJson(101, "生成订单失败，请重试", "");
	// }
	// break;
	// case 1:// 支付宝
	// break;
	// default:
	// break;
	// }
	// // data.put("orderNum", orderNum);
	// if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 21) {
	// return ResultUtils.toJson(100, "生成订单成功，超过21点的订单将隔天下午送达", "");
	// }
	// if(data!=null){
	// return ResultUtils.toJson(100, "生成订单成功", data);
	// }
	// return ResultUtils.toJson(100, "生成订单成功", "");
	// } else {
	// return ResultUtils.toJson(101, "生成订单失败，请重试", "");
	// }
	// }

}
