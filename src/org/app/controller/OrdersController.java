package org.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.bean.CartBean;
import org.bean.OrderModel;
import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.OrdersGiftDao;
import org.dao.RefundDao;
import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.OrdersGiftDaoImp;
import org.dao.imp.UserAddressDaoImp;
import org.dao.imp.UserDaoImp;
import org.model.Orders;
import org.model.OrdersDetail;
import org.model.OrdersGift;
import org.model.Refund;
import org.model.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ALIPAY;
import org.util.ChangeTime;
import org.util.JsonUtils;
import org.util.RedisUtil;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.util.Utils;
import org.util.WXAPI;
import org.view.VOrdersDetailsId;
import org.view.VOrdersGiftId;
import org.view.VOrdersId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("/app/OrdersController")
@RequestMapping("/app/orders")
public class OrdersController {
	OrdersDao oDao;
	GoodsDao gDao;
	RefundDao rDao;
	UserAddressDao uAddressDao;
	UserDao uDao;
	OrdersGiftDao ordersGiftDao;
	// Long userid;
	Map<String, Object> data;

	@RequestMapping("/getOrdersList")
	@ResponseBody
	public Object getOrdersList(HttpServletRequest request, Integer start,
			Integer limit, Integer state, Integer type) throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (type == null)
			type = 0;
		List<VOrdersId> list = oDao.getList(userid, start, limit, state, type);
		data = new HashMap<String, Object>();
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			for (VOrdersId order : list) {
				order.setDetails(oDao.getDetailList(order.getId(), start, -1,
						null));
				order.setUrlList(oDao.getUrlList(order.getId()));
			}
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrderById")
	@ResponseBody
	public Object getOrderById(HttpServletRequest request, Long id)
			throws Exception {
		oDao = new OrdersDaoImp();
		VOrdersId v = oDao.getOrder(id);
		if (v != null) {
			List<VOrdersDetailsId> details = oDao.getDetailList(v.getId(), 0,
					-1, null);
			v.setDetails(details);
		}
		data = new HashMap<String, Object>();
		data.put("order", v);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrdersDetailList")
	@ResponseBody
	public Object getOrdersDetailList(Long orderId, Integer start, Integer limit)
			throws Exception {
		oDao = new OrdersDaoImp();
		List<VOrdersDetailsId> list = oDao.getDetailList(orderId, start, limit,
				null);
		ordersGiftDao = new OrdersGiftDaoImp();
		List<VOrdersGiftId> giftList = ordersGiftDao.getAllByOrderId(orderId);
		data = new HashMap<>();
		data.put("list", list);
		data.put("giftList", giftList);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOrdersListByIsComment")
	@ResponseBody
	public Object getOrdersListByIsComment(HttpServletRequest request,
			Integer start, Integer limit, Integer isComment, Integer type)
			throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (type == null)
			type = 0;
		List<VOrdersId> list = oDao.getListByIsComment(userid, start, limit,
				isComment, type);
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

	@RequestMapping("/getRefundList")
	@ResponseBody
	public Object getRefundList(HttpServletRequest request, Integer start,
			Integer limit, Integer type) throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (type == null)
			type = 0;
		List<VOrdersId> list = oDao.getList(userid, start, limit, type);
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

	@RequestMapping("/getOrders")
	@ResponseBody
	public Object getOrders(HttpServletRequest request, Integer start,
			Integer limit, Integer state, Integer type) throws Exception {
		oDao = new OrdersDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (type == null)
			type = 0;
		List<VOrdersId> list = oDao.getList(userid, start, limit, state, type);
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

	/**
	 * @param selectIds
	 *            参数格式为活动Id+"-"+商品Id
	 */
	private Map<String, Object> getDetailsAndBenefit(Long userid,
			List<String> selectIds) throws JsonParseException,
			JsonMappingException, IOException {
		gDao = new GoodsDaoImp();
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		Map<String, Object> result = new HashMap<>();
		List<OrdersDetail> orderList = new ArrayList<>();
		List<OrdersGift> giftList = new ArrayList<>();
		Set<String> benefit = new HashSet<String>();
		StringBuffer sb = new StringBuffer();
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (CartBean cartBean : cartList) {
				Iterator<OrdersDetail> iterator = cartBean.getList().iterator();
				if (cartBean.getGift() != null) {
					OrdersDetail od1 = cartBean.getGift();
					giftList.add(new OrdersGift(null, null, od1.getGoodsId(),
							od1.getActId()));
				}
				while (iterator.hasNext()) {
					OrdersDetail od = iterator.next();
					if (od.getIsSelect()) {// 从购物车中移除选择的商品列表
						if (!cartBean.getName().equals("")) {
							benefit.add(cartBean.getName());
						}
						RedisUtil.del(
								userid + "-cartList-" + cartBean.getActId(),
								od.getGoodsId() + "");
						iterator.remove();
						orderList.add(od);
					}
					if (gDao.getGoods(od.getGoodsId(), od.getTime(), (short) 1) == null) {
						if (sb.length() == 0) {
							sb.append(od.getName());
						} else {
							sb.append("、" + od.getName());
						}
					}
				}
			}
			RedisUtil.addData("cart-" + userid,
					mapper.writeValueAsString(cartList), null);
		}
		result.put("details", orderList);
		result.put("benefit", benefit.size() == 0 ? "无" : benefit.toString());
		result.put("giftList", giftList);
		result.put("illegalList", sb.toString());
		return result;
	}

	@RequestMapping("/addVegetableOrder")
	@ResponseBody
	public Object addVegetableOrder(HttpServletRequest request,
			@RequestBody OrderModel o) {
		oDao = new OrdersDaoImp();
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		Long time = System.currentTimeMillis();
		String orderNum = time + Utils.ran6();

		Map<String, Object> result = null;
		try {
			result = getDetailsAndBenefit(userid, o.getSelectIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String illegalList = (String) result.get("illegalList");
		if (illegalList.length() > 0) {
			return ResultUtils.toJson(101, "您的购物车中如下商品信息已过期，请重新选购:"
					+ illegalList.toString(), "");
		}
		String benefit = (String) result.get("benefit");
		List<OrdersDetail> details = (List<OrdersDetail>) result.get("details");
		List<OrdersGift> giftList = (List<OrdersGift>) result.get("giftList");
		/*********************************/
		Orders orders = new Orders(orderNum, userid, time / 1000,
				uAddressDao.getAddressById(o.getAddressId()),
				o.getFranchiseeId(), o.getType(), o.getRemarks(), o.getTotal(),
				benefit);
		uDao = new UserDaoImp();
		User user = uDao.getUser(userid);
		boolean isFree = false;
		if (o.getType() == 0 && user.getIsFree() == 1 && o.getTotal() < 50d) {// 有免单特权且总价低于50
			Integer count = Utils.parseInt(RedisUtil.getData("free-" + userid));
			if (count == null) {// 当天未免单，则免单
				orders.setPayWay(2);
				RedisUtil.addData("free-" + userid, "" + 1,
						ChangeTime.weekendTime(24, time));
				isFree = true;
			} else if (count < 3) { // 本周有下单且免单数小于3，则免单
				RedisUtil.addData("free-" + userid, "" + (++count),
						ChangeTime.weekendTime(24, time));
				orders.setPayWay(2);
				isFree = true;
			}
		}
		System.out.println(o.getAddressId() + "|||" + orders.getAddress());
		if (details == null || details.size() == 0) {
			return ResultUtils.toJson(101, "生成订单失败，请重试", "");
		}
		Long id = oDao.generateOrder(orders, details, giftList);
		if (id > 0) {
			data = new HashMap<>();
			data.put("orderNum", orderNum);
			String msg = "生成订单成功";
			if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 21) {
				msg = "生成订单成功，超过21点的订单将于后天送达";
			}
			if (isFree) {
				msg = msg + "，您在体验期间享受免单特权，每周限免3单，每单限购50元";
			}
			return ResultUtils.toJson(100, msg, data);
		} else {
			return ResultUtils.toJson(101, "生成订单失败，请重试", "");
		}
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
		Orders orders = new Orders(orderNum, userid, time / 1000,
				uAddressDao.getAddressById(o.getAddressId()),
				o.getFranchiseeId(), o.getType(), o.getRemarks(), o.getTotal(),
				"无");
		uDao = new UserDaoImp();
		System.out.println(o.getAddressId() + "|||" + orders.getAddress());
		Long id = oDao.generateOrder(orders, o.getDetails(), null);
		if (id > 0) {
			Double Realtotal = oDao.getTotal(id);
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
