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
import org.dao.RefundDao;
import org.dao.UserAddressDao;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserAddressDaoImp;
import org.model.Orders;
import org.model.Refund;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ALIPAY;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.util.Utils;
import org.util.WXAPI;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

@Controller("/app/DishOrdersController")
@RequestMapping("/app/dishorders")
public class DishOrdersController {
	OrdersDao oDao;
	GoodsDao gDao;
	RefundDao rDao;
	UserAddressDao uAddressDao;
	// Long userid;
	Map<String, Object> data;

	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request, Integer start,
			Integer limit, Integer state) throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		List<VOrdersId> list = oDao.getList(userid, start, limit, state,false);
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
			Integer limit, Integer state) throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		List<VOrdersId> list = oDao.getList(userid, start, limit, state,false);
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
		if (oDao.cancel(userid, id) > 0) {
			return ResultUtils.toJson(100, "取消订单成功", "");
		}
		return ResultUtils.toJson(101, "取消订单失败，您的订单已取消或系统繁忙，请稍后重试", "");
	}

	@RequestMapping("/finishOrder")
	@ResponseBody
	public Object finishOrder(HttpServletRequest request, Long id)
			throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (oDao.updateDeliveryState(2, userid, id)) {
			return ResultUtils.toJson(100, "订单完成！", "");
		}
		return ResultUtils.toJson(101, "服务器繁忙，请您重试", "");
	}

	@RequestMapping("/refundOrder")
	@ResponseBody
	public Object refundOrder(Long id, Integer payWay, String reason,
			Double totalFee) throws Exception {
		oDao = new OrdersDaoImp();
		Long time = System.currentTimeMillis() / 1000;
		String refundId = time + Utils.ran4();
		Refund refund = new Refund(refundId, id, totalFee, 1, reason, time);
		switch (oDao.updateRefundId(id, refund, 0)) {
		case 0:
			return ResultUtils.toJson(101, "该订单尚未付款，无法进行退款操作，请重试", "");
		case 1:
			return ResultUtils.toJson(100, "取消订单成功，您的退款将在1~5个工作日内返还", "");
		case -1:
			return ResultUtils.toJson(101, "系统繁忙，请稍后重试或咨询客服", "");
		case -2:// 超过当天9点
			return ResultUtils.toJson(101, "该订单在当天9点后无法取消", "");
		}
		return ResultUtils.toJson(101, "生成退款单失败，请重试", "");
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
		Orders orders = new Orders(userid, time / 1000,
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

	/**
	 * 定时自动完成
	 */
	@Scheduled(fixedDelay = 1 * 1000)
	private void finishOrder() {
		oDao = new OrdersDaoImp();
		oDao.finish();
	}

	/**
	 * 定时取消
	 */
	@Scheduled(fixedDelay = 1 * 1000)
	private void closeOrder() {
		oDao = new OrdersDaoImp();
		oDao.cancel();
	}

	@RequestMapping("/doPay")
	@ResponseBody
	public Object doPay(HttpServletRequest request, String orderNum,
			Integer payWay) throws Exception {
		oDao = new OrdersDaoImp();
		// System.out.println(payWay + "" + orderNum);
		VOrdersId v = oDao.getOrder(orderNum);
		if (v.getState() == 0) {
			return ResultUtils.toJson(101,
					"该订单因超时或其他原因被关闭，请您重新下单，如有疑问可拨打客服电话咨询我们", "");
		}
		Double Realtotal = v.getTotal();
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
			data = new HashMap<>();
			String result = ALIPAY.doPay(orderNum, "" + Realtotal);
			if (result == null) {
				return ResultUtils.toJson(101, "发起支付失败，请重试", "");
			}
			data.put("result", result);
			return ResultUtils.toJson(100, "", data);
		default:
			return ResultUtils.toJson(101, "目前只支持微信支付", "");
		}
	}

}
