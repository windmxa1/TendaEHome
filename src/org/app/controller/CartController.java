package org.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bean.CartBean;
import org.dao.ActivityDao;
import org.dao.GoodsDao;
import org.dao.imp.ActivityDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.model.OrdersDetail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.JsonUtils;
import org.util.RedisUtil;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VActivityId;
import org.view.VGoodsId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController("app/CartController")
@RequestMapping("app/cart")
public class CartController {
	private GoodsDao gDao;
	private ActivityDao aDao;

	/**
	 * @param selectIds
	 *            参数格式为活动Id+"-"+商品Id
	 */
	@RequestMapping("deleteSelect")
	public Object deleteSelect(HttpServletRequest request, String[] selectIds)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			List<Integer> removeIndexs = new ArrayList<>();
			int j = 0;
			for (CartBean cartBean : cartList) {// 删除数组元素的方法，放在循环之外，否则一旦索引被改变，则无法通过下标找到正确的元素
				Iterator<OrdersDetail> iterator = cartBean.getList().iterator();
				while (iterator.hasNext()) {
					OrdersDetail od = iterator.next();
					if (Arrays.asList(selectIds).contains(
							cartBean.getActId() + "-" + od.getGoodsId())) {// 从购物车中移除选择的商品列表
						iterator.remove();
					}
				}
				if (cartBean.getList().size() == 0) {
					removeIndexs.add(j);
				} else {
					cartBean.setList(cartBean.getList());
				}
				j++;
			}
			Iterator<CartBean> iterator = cartList.iterator();
			int m = 0;
			while (iterator.hasNext()) {
				iterator.next();
				if (removeIndexs.contains(m)) {// 包含了需要删除的活动下标，则移除
					iterator.remove();
				}
				m++;
			}
		} else {
			cartList = new ArrayList<>();
		}
		RedisUtil.addData("cart-" + userid,
				mapper.writeValueAsString(cartList), null);
		return ResultUtils.toJson(100, "", cartList);
	}

	@RequestMapping("selectAllOrNot")
	public Object selectAllOrNot(HttpServletRequest request, boolean selectAll)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (CartBean cartBean : cartList) {
				for (OrdersDetail od : cartBean.getList()) {
					od.setIsSelect(selectAll);
				}
			}
		} else {
			cartList = new ArrayList<>();
		}
		RedisUtil.addData("cart-" + userid,
				mapper.writeValueAsString(cartList), null);
		return ResultUtils.toJson(100, "", cartList);
	}

	@RequestMapping("selectGoods")
	public Object selectGoods(HttpServletRequest request, OrdersDetail od1)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (CartBean cartBean : cartList) {
				if (cartBean.getActId().equals(od1.getActId())) {// 活动存在
					List<OrdersDetail> list = cartBean.getList();
					for (OrdersDetail od : list) {
						if (od.getGoodsId().equals(od1.getGoodsId())) {// 商品存在
							od.setIsSelect(od1.getIsSelect());
							break;
						}
					}
					cartBean.setList(list);
				}
			}
		} else {
			cartList = new ArrayList<>();
		}
		RedisUtil.addData("cart-" + userid,
				mapper.writeValueAsString(cartList), null);
		return ResultUtils.toJson(100, "", cartList);
	}

	@RequestMapping("selectActivity")
	public Object selectActivity(HttpServletRequest request, OrdersDetail od1)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (CartBean cartBean : cartList) {
				if (cartBean.getActId().equals(od1.getActId())) {// 活动存在
					List<OrdersDetail> list = cartBean.getList();
					for (OrdersDetail od : list) {
						od.setIsSelect(od1.getIsSelect());
					}
					cartBean.setList(list);
				}
			}
		} else {
			cartList = new ArrayList<>();
		}
		RedisUtil.addData("cart-" + userid,
				mapper.writeValueAsString(cartList), null);
		return ResultUtils.toJson(100, "", cartList);
	}

	@RequestMapping("getCart")
	public Object getCart(HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		List<CartBean> cartList = null;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			ObjectMapper mapper = JsonUtils.getMapperInstance();
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
		}
		return ResultUtils.toJson(100, "", cartList == null ? new ArrayList<>()
				: cartList);
	}

	@RequestMapping("modifyGoods")
	public Object modifyGoods(HttpServletRequest request, Integer actId,
			Long goodsId, Integer newActId, String newActName,
			Double newMinPrice) throws JsonParseException,
			JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			int newIndex = -1;
			int oldIndex = -1;
			OrdersDetail od2 = null;
			// #####坑点#####无法确认先遍历到的是旧活动还是新活动
			for (int j = 0; j < cartList.size(); j++) {
				CartBean cartBean = cartList.get(j);
				if (cartBean.getActId().equals(actId)) {// 旧活动存在且一定存在
					oldIndex = j;
					for (OrdersDetail od : cartBean.getList()) {
						if (od.getGoodsId().equals(goodsId)) {
							od2 = od;
							od2.setActId(newActId);
							od2.setActName(newActName);
							od2.setActMinPrice(newMinPrice);
							cartBean.getList().remove(od);
							break;
						}
					}
				}
				if (cartBean.getActId().equals(newActId)) {// 新活动存在
					newIndex = j;
				}
			}
			if (newIndex == -1) {// 新活动不存在
				CartBean cartBean = new CartBean();
				cartBean.setActId(newActId);
				cartBean.setName(newActName);
				cartBean.setMinPrice(newMinPrice);
				List<OrdersDetail> list = new ArrayList<>();
				list.add(od2);
				cartBean.setList(list);
				cartList.add(cartBean);
			} else {// 新活动存在，则把商品加到新活动的数组中
				boolean isGoodExist = false;
				for (OrdersDetail od : cartList.get(newIndex).getList()) {
					if (od.getGoodsId().equals(goodsId)) {// 新活动中已存在该商品，直接将该商品的数目和原商品相加
						od.setNum(od.getNum() + od2.getNum());
						isGoodExist = true;
						break;
					}
				}
				if (!isGoodExist) {// 新活动中不存在该商品，则加入该商品
					cartList.get(newIndex).getList().add(od2);
				}
			}
			if (cartList.get(oldIndex).getList().size() == 0) {// 当活动组中商品数目为0，则删除该活动组
				cartList.remove(oldIndex);
			}
		} else {
			cartList = new ArrayList<>();
		}
		RedisUtil.addData("cart-" + userid,
				mapper.writeValueAsString(cartList), null);
		return ResultUtils.toJson(100, "", cartList);
	}

	@RequestMapping("modifyNum")
	public Object modifyNum(HttpServletRequest request, OrdersDetail od1)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (CartBean cartBean : cartList) {
				if (cartBean.getActId().equals(od1.getActId())) {// 活动存在
					List<OrdersDetail> list = cartBean.getList();
					for (OrdersDetail od : list) {
						if (od.getGoodsId().equals(od1.getGoodsId())) {// 商品存在
							od.setNum(od1.getNum());
							od.setPrices(od.getPrice() * od.getNum());
							break;
						}
					}
					cartBean.setList(list);
				}
			}
		} else {
			cartList = new ArrayList<>();
		}
		RedisUtil.addData("cart-" + userid,
				mapper.writeValueAsString(cartList), null);
		return ResultUtils.toJson(100, "", cartList);
	}

	/**
	 * 设置赠品
	 * 
	 * @param od1
	 *            其中actid由购物车界面传入,isGift需要设置为true
	 */
	@RequestMapping("setGift")
	public Object setGift(OrdersDetail od1, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (CartBean cartBean : cartList) {
				if (cartBean.getActId().equals(od1.getActId())) {// 活动存在
					cartBean.setGift(od1);
					RedisUtil.addData("cart-" + userid,
							mapper.writeValueAsString(cartList), null);
					return ResultUtils.toJson(100, "", cartList);
				}
			}
		}
		return ResultUtils.toJson(101, "服务器繁忙，请重试", "");

	}

	private OrdersDetail getOrderDetail(OrdersDetail od1) {
		gDao = new GoodsDaoImp();
		aDao = new ActivityDaoImp();
		VActivityId a = aDao.getById(od1.getActId());
		VGoodsId gd = gDao.getVGoods(od1.getGoodsId());
		OrdersDetail od2 = new OrdersDetail();
		od2.setActId(od1.getActId());
		od2.setGoodsId(gd.getGoodsId());
		od2.setActMinPrice(a.getMinPrice());
		od2.setActName(a.getTitle());
		od2.setGoodsUrl(gd.getGoodsUrl());
		od2.setPrice(gd.getPrice());
		od2.setTime(gd.getTime());
		od2.setIsSelect(true);
		od2.setIsShopSelect(true);
		return od2;
	}

	@RequestMapping("addObj")
	public Object addObj(OrdersDetail od1, HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			Boolean isActExist = false;
			Boolean isExist = false;
			for (CartBean cartBean : cartList) {
				if (cartBean.getActId().equals(od1.getActId())) {// 活动存在
					isActExist = true;
					List<OrdersDetail> list = cartBean.getList();
					for (OrdersDetail od : list) {
						if (od.getGoodsId().equals(od1.getGoodsId())) {// 商品存在，数量加1
							od.setNum(od.getNum() + 1);
							od.setPrices(od.getNum() * od.getPrice());
							isExist = true;
							break;
						}
					}
					if (!isExist) {// 商品不存在，则添加新的商品进去
						System.out.println("商品不存在");
						OrdersDetail od2 = getOrderDetail(od1);
						list.add(od2);
					}
					cartBean.setList(list);
				}
			}
			if (!isActExist) {//
				System.out.println("活动不存在");
				OrdersDetail od2 = getOrderDetail(od1);
				CartBean cartBean = new CartBean();
				cartBean.setActId(od2.getActId());
				cartBean.setName(od2.getActName());
				cartBean.setMinPrice(od2.getActMinPrice());
				List<OrdersDetail> list = new ArrayList<OrdersDetail>();
				list.add(od2);
				cartBean.setList(list);
				cartList.add(cartBean);
			}
			RedisUtil.addData("cart-" + userid,
					mapper.writeValueAsString(cartList), null);
		} else {// 购物车不存在
			System.out.println("购物车不存在");
			OrdersDetail od2 = getOrderDetail(od1);
			cartList = new ArrayList<>();
			CartBean cartBean = new CartBean();
			cartBean.setActId(od2.getActId());
			cartBean.setName(od2.getActName());
			List<OrdersDetail> list = new ArrayList<OrdersDetail>();
			list.add(od2);
			cartBean.setList(list);
			cartList.add(cartBean);
			RedisUtil.addData("cart-" + userid,
					mapper.writeValueAsString(cartList), null);
		}
		return ResultUtils.toJson(100, "", cartList);
	}

	@RequestMapping("delete")
	public Object delete(HttpServletRequest request, OrdersDetail od1)
			throws JsonParseException, JsonMappingException, IOException {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (int j = 0; j < cartList.size(); j++) {
				CartBean cartBean = cartList.get(j);
				if (cartBean.getActId().equals(od1.getActId())) {// 活动存在
					List<OrdersDetail> list = cartBean.getList();
					for (int k = 0; k < list.size(); k++) {
						OrdersDetail od = list.get(k);
						if (od.getGoodsId().equals(od1.getGoodsId())) {// 商品存在
							if (list.size() == 1) {
								cartList.remove(cartBean);
							} else {
								list.remove(od);
							}
							break;
						}
					}
				}
			}
		} else {
			cartList = new ArrayList<>();
		}
		RedisUtil.addData("cart-" + userid,
				mapper.writeValueAsString(cartList), null);
		return ResultUtils.toJson(100, "", cartList);
	}

	/**
	 * 计算订单促销价格
	 */
	@RequestMapping("getTotalPrice")
	public Object getTotalPrice(HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		ActivityDao aDao = new ActivityDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		List<CartBean> cartList;
		Double totalPrice = 0d;
		List<OrdersDetail> list = new ArrayList<>();
		List<OrdersDetail> giftList = new ArrayList<>();
		StringBuilder msg = new StringBuilder();
		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
					CartBean.class);
			cartList = mapper.readValue(cartListStr, javaType);
			for (CartBean cartBean : cartList) {
				if (cartBean.getGift() != null) {
					giftList.add(cartBean.getGift());
				}
				// 遍历商品数组，确认活动类型
				String type = "";
				VActivityId activity = null;
				if (cartBean.getActId() != 0 && cartBean.getActId() != null) {
					activity = aDao.getById(cartBean.getActId());
					type = activity.getTypeName();
				}
				switch (type) {// 判断活动类型
				case "满减":
					Double total1 = 0d;
					for (OrdersDetail od : cartBean.getList()) {
						if (od.getIsSelect()) {
							list.add(od);
							total1 += od.getPrices();
						}
					}
					if (activity.getMinPrice() < total1) {
						totalPrice = totalPrice + total1 - activity.getNum();
					} else {
						totalPrice += total1;
					}
					break;
				case "满赠":
					Double total2 = 0d;
					if (cartBean.getGift() == null) {
						msg.append("活动").append(cartBean.getName()).append(" ");
					}
					for (OrdersDetail od : cartBean.getList()) {
						if (od.getIsSelect()) {
							list.add(od);
							total2 += od.getPrices();
						}
						totalPrice += total2;
					}
					break;
				case "折扣":
					Double total3 = 0d;
					for (OrdersDetail od : cartBean.getList()) {
						if (od.getIsSelect()) {
							list.add(od);
							total3 += od.getPrices() * activity.getNum() / 100;
						}
					}
					totalPrice += total3;
					break;
				case "":
					Double total4 = 0d;
					for (OrdersDetail od : cartBean.getList()) {
						if (od.getIsSelect()) {
							list.add(od);
							total4 += od.getPrices();
						}
					}
					totalPrice += total4;
					break;
				}
			}
		}
		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		data.put("total", totalPrice);
		data.put("giftList", giftList);
		if (msg.length() > 0) {
			return ResultUtils.toJson(101, msg.append("没有选择赠品").toString(),
					data);
		}
		return ResultUtils.toJson(100, "", data);
	}
	// @RequestMapping("subObj")
	// public Object subObj(HttpServletRequest request, OrdersDetail od1)
	// throws JsonParseException, JsonMappingException, IOException {
	// /**** 获取header中的token并取出userid ****/
	// String token = request.getHeader("token");
	// Long userid = Long.parseLong(""
	// + TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
	// /*********************************/
	// String cartListStr = RedisUtil.getData("cart-" + userid);
	// ObjectMapper mapper = JsonUtils.getMapperInstance();
	// List<CartBean> cartList;
	// if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
	// JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
	// CartBean.class);
	// cartList = mapper.readValue(cartListStr, javaType);
	// for (CartBean cartBean : cartList) {
	// if (cartBean.getActId() == od1.getActId()) {// 活动存在
	// List<OrdersDetail> list = cartBean.getList();
	// for (OrdersDetail od : list) {
	// if (od.getGoodsId() == od1.getGoodsId()) {// 商品存在，数量加1
	// if (od.getNum() != 1) {
	// od.setNum(od.getNum() - 1);
	// }
	// break;
	// }
	// }
	// cartBean.setList(list);
	// }
	// }
	// } else {
	// cartList = new ArrayList<>();
	// }
	// RedisUtil.addData("cart-" + userid,
	// mapper.writeValueAsString(cartList), null);
	// return ResultUtils.toJson(100, "", cartList);
	// }
}
