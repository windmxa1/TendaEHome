package org.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.bean.CartBean;
import org.model.OrdersDetail;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.JsonUtils;
import org.util.RedisUtil;
import org.util.ResultUtils;
import org.util.TokenUtils;

import com.alipay.api.domain.OrderDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController("app/CartController")
@RequestMapping("app/cart")
public class CartController {
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

	// @RequestMapping("addOrder")
	// /**
	// * @param selectIds 参数格式为活动Id+"-"+商品Id
	// */
	// public Object addOrder(HttpServletRequest request, List<String>
	// selectIds)
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
	// Iterator<OrdersDetail> iterator = cartBean.getList().iterator();
	// List<OrdersDetail> orderList = new ArrayList<>();
	// while (iterator.hasNext()) {
	// OrdersDetail od = iterator.next();
	// if (selectIds.contains(cartBean.getActId() + "-"
	// + od.getGoodsId())) {// 从购物车中移除选择的商品列表
	// iterator.remove();
	// orderList.add(od);
	// }
	// }
	// cartList.add();
	// orderMap.put(actId, orderList);
	// }
	// RedisUtil.addData("cart-" + userid,
	// mapper.writeValueAsString(cartList), null);
	// }
	// return ResultUtils.toJson(100, "", "");
	// }

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
			Long goodsId, Integer newActId, String newActName)
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
			int newIndex = -1;
			OrdersDetail od2 = null;
			for (int j = 0; j < cartList.size(); j++) {
				CartBean cartBean = cartList.get(j);
				if (cartBean.getActId() == actId) {// 旧活动存在且一定存在
					List<OrdersDetail> list = cartBean.getList();
					for (int k = 0; k < cartBean.getList().size(); k++) {
						OrdersDetail od = cartBean.getList().get(k);
						if (od.getGoodsId() == goodsId) {
							od2 = od;
							od2.setActId(newActId);
							od2.setActName(newActName);
							if (list.size() == 1) {
								cartList.remove(cartBean);
							} else {
								cartBean.getList().remove(od);
							}
							break;
						}
					}

				}
				if (cartBean.getActId() == newActId) {// 新活动存在
					System.out.println(cartBean.getActId());
					newIndex = j;
				}
			}
			if (newIndex == -1) {// 新活动不存在
				CartBean cartBean = new CartBean();
				cartBean.setActId(newActId);
				cartBean.setName(newActName);
				List<OrdersDetail> list = new ArrayList<>();
				list.add(od2);
				cartBean.setList(list);
				cartList.add(cartBean);
			} else {// 新活动存在，则把商品加到新活动的数组中
				cartList.get(newIndex).getList().add(od2);
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
				if (cartBean.getActId() == od1.getActId()) {// 活动存在
					List<OrdersDetail> list = cartBean.getList();
					for (OrdersDetail od : list) {
						if (od.getGoodsId() == od1.getGoodsId()) {// 商品存在
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
				if (cartBean.getActId() == od1.getActId()) {// 活动存在
					isActExist = true;
					List<OrdersDetail> list = cartBean.getList();
					for (OrdersDetail od : list) {
						if (od.getGoodsId() == od1.getGoodsId()) {// 商品存在，数量加1
							od.setNum(od.getNum() + 1);
							od.setPrices(od.getNum() * od.getPrice());
							isExist = true;
							break;
						}
					}
					if (!isExist) {// 商品不存在，则添加新的商品进去
						System.out.println("商品不存在");
						list.add(od1);
					}
					cartBean.setList(list);
				}
			}
			if (!isActExist) {//
				System.out.println("活动不存在");
				CartBean cartBean = new CartBean();
				cartBean.setActId(od1.getActId());
				cartBean.setName(od1.getActName());
				List<OrdersDetail> list = new ArrayList<OrdersDetail>();
				list.add(od1);
				cartBean.setList(list);
				cartList.add(cartBean);
			}
			RedisUtil.addData("cart-" + userid,
					mapper.writeValueAsString(cartList), null);
		} else {// 购物车不存在
			System.out.println("购物车不存在");
			cartList = new ArrayList<>();
			CartBean cartBean = new CartBean();
			cartBean.setActId(od1.getActId());
			cartBean.setName(od1.getActName());
			List<OrdersDetail> list = new ArrayList<OrdersDetail>();
			list.add(od1);
			cartBean.setList(list);
			cartList.add(cartBean);
			RedisUtil.addData("cart-" + userid,
					mapper.writeValueAsString(cartList), null);
		}
		return ResultUtils.toJson(100, "", cartList);
	}

	// @RequestMapping("addObj")
	// public Object addObj(OrdersDetail od1, HttpServletRequest request)
	// throws JsonParseException, JsonMappingException, IOException {
	// System.out.println(JsonUtils.getMapperInstance()
	// .writeValueAsString(od1));
	// /**** 获取header中的token并取出userid ****/
	// String token = request.getHeader("token");
	// Long userid = Long.parseLong(""
	// + TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
	// /*********************************/
	//
	// String cartListStr = RedisUtil.getData("cart-" + userid);
	// ObjectMapper mapper = JsonUtils.getMapperInstance();
	// Map<String, List<OrdersDetail>> cartList;
	// if (cartListStr != null&&!cartListStr.equals("")) {// 购物车存在
	// cartList = mapper.readValue(cartListStr, HashMap.class);
	// JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
	// OrdersDetail.class);
	// Boolean isExist = false;
	// if (cartList.get("" + od1.getActId()) != null) {// 活动存在
	// String s1 = mapper.writeValueAsString(cartList.get(""
	// + od1.getActId()));
	// // System.out.println(s1);
	// List<OrdersDetail> list = (List<OrdersDetail>) mapper
	// .readValue(s1, javaType);
	// for (OrdersDetail od : list) {
	// if (od.getGoodsId() == od1.getGoodsId()) {// 商品存在
	// od.setNum(od.getNum() + 1);
	// isExist = true;
	// break;
	// }
	// }
	// if (!isExist) {// 商品不存在
	// // OrdersDetail od = new OrdersDetail(null, od1, 1);
	// // od.setActId(actId);
	// list.add(od1);
	// }
	// cartList.put("" + od1.getActId(), list);
	// } else {// 活动不存在
	// System.out.println("不存在1");
	// System.out.println(cartList.get("" + od1.getActId()));
	// // OrdersDetail od = new OrdersDetail(null, id, 1, actId);
	// List<OrdersDetail> list = new ArrayList<OrdersDetail>();
	// list.add(od1);
	// cartList.put("" + od1.getActId(), list);
	// }
	// RedisUtil.addData("cart-" + userid,
	// mapper.writeValueAsString(cartList), null);
	// } else {// 购物车不存在
	// System.out.println("不存在2");
	// cartList = new HashMap<>();
	// // OrdersDetail od = new OrdersDetail(null, id, 1);
	// List<OrdersDetail> list = new ArrayList<OrdersDetail>();
	// list.add(od1);
	// cartList.put(od1.getActId() + "", list);
	// RedisUtil.addData("cart-" + userid,
	// mapper.writeValueAsString(cartList), null);
	// }
	// return ResultUtils.toJson(100, "", cartList);
	// }

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
				if (cartBean.getActId() == od1.getActId()) {// 活动存在
					List<OrdersDetail> list = cartBean.getList();
					for (int k = 0; k < list.size(); k++) {
						OrdersDetail od = list.get(k);
						if (od.getGoodsId() == od1.getGoodsId()) {// 商品存在
							if (list.size() == 1) {
								cartList.remove(cartBean);
							} else {
								list.remove(od);
								// cartList.("" + actId, list);
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

//	@RequestMapping("subObj")
//	public Object subObj(HttpServletRequest request, OrdersDetail od1)
//			throws JsonParseException, JsonMappingException, IOException {
//		/**** 获取header中的token并取出userid ****/
//		String token = request.getHeader("token");
//		Long userid = Long.parseLong(""
//				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
//		/*********************************/
//		String cartListStr = RedisUtil.getData("cart-" + userid);
//		ObjectMapper mapper = JsonUtils.getMapperInstance();
//		List<CartBean> cartList;
//		if (cartListStr != null && !cartListStr.equals("")) {// 购物车存在
//			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
//					CartBean.class);
//			cartList = mapper.readValue(cartListStr, javaType);
//			for (CartBean cartBean : cartList) {
//				if (cartBean.getActId() == od1.getActId()) {// 活动存在
//					List<OrdersDetail> list = cartBean.getList();
//					for (OrdersDetail od : list) {
//						if (od.getGoodsId() == od1.getGoodsId()) {// 商品存在，数量加1
//							if (od.getNum() != 1) {
//								od.setNum(od.getNum() - 1);
//							}
//							break;
//						}
//					}
//					cartBean.setList(list);
//				}
//			}
//		} else {
//			cartList = new ArrayList<>();
//		}
//		RedisUtil.addData("cart-" + userid,
//				mapper.writeValueAsString(cartList), null);
//		return ResultUtils.toJson(100, "", cartList);
//	}
}
