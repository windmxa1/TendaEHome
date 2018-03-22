package org.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bean.CartBean;
import org.dao.GoodsDao;
import org.dao.imp.GoodsDaoImp;
import org.model.GoodsCatalog;
import org.model.OrdersDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.JsonUtils;
import org.util.RedisUtil;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VGoodsId;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("/app/GoodsController")
@RequestMapping("/app/goods")
public class GoodsController {
	GoodsDao gDao;
	Map<String, Object> data;

	// 获取商品信息
	@RequestMapping("/getGoodsList")
	@ResponseBody
	public Object getGoodsList(Integer start, Integer limit, Integer type)
			throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		Short[] state = { (short) 1 };
		if (type == null)
			type = 0;
		List<VGoodsId> list = gDao.getList(start, limit, state, type);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/checkGoods")
	@ResponseBody
	public Object checkGoods(HttpServletRequest request, String[] selectIds)
			throws Exception {
		StringBuffer list = new StringBuffer();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String cartListStr = RedisUtil.getData("cart-" + userid);
		ObjectMapper mapper = JsonUtils.getMapperInstance();
		JavaType javaType = JsonUtils.getCollectionType(ArrayList.class,
				CartBean.class);
		List<CartBean> cartList = mapper.readValue(cartListStr, javaType);
		gDao = new GoodsDaoImp();
		for (CartBean cartBean : cartList) {
			for (OrdersDetail od : cartBean.getList()) {
				if (gDao.getGoods(od.getGoodsId(), od.getTime(), (short) 1) == null) {
					if (list.length() == 0) {
						list.append(od.getName());
					} else {
						list.append("、" + od.getName());
					}
				}
			}
		}
		if (list.length() > 0) {
			return ResultUtils.toJson(101,
					"您的购物车中如下商品信息已过期，请重新选购:" + list.toString(), "");
		}
		return ResultUtils.toJson(100, "", "");
	}

	// 获取销量最高的10个商品
	@RequestMapping("/getBestSell")
	@ResponseBody
	public Object getGoodsList(Integer type) throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		Short[] state = { (short) 1 };
		if (type == null)
			type = 0;
		List<VGoodsId> list = gDao.getList(0, 10, state, type);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 获取折扣商品信息
	@RequestMapping("/getDiscounts")
	@ResponseBody
	public Object getDiscounts(Integer start, Integer limit, Integer type)
			throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		if (type == null)
			type = 0;
		List<VGoodsId> list = gDao.getDiscounts(start, limit, type);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 搜索商品
	@RequestMapping("/searchGoods")
	@ResponseBody
	public Object searchGoods(Integer start, Integer limit, String key,
			Integer type) throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		Short[] state = { (short) 1 };
		if (type == null)
			type = 0;
		List<VGoodsId> list = gDao
				.getGoodsByKey(start, limit, key, state, type);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 最新上架
	@RequestMapping("/getNewArrival")
	@ResponseBody
	public Object getNewArrival(Integer type) throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		if (type == null)
			type = 0;
		List<VGoodsId> list = gDao.getNewArrival(type);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 最新上架，按分类返回
	@RequestMapping("/getNewArrival1")
	@ResponseBody
	public Object getNewArrival1(Integer type) throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		if (type == null)
			type = 0;
		List<Long> gCatalogIds = gDao.getCatalogIds(type);
		if (gCatalogIds == null) {
			return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
		}
		for (Long catalog : gCatalogIds) {
			List<VGoodsId> list = gDao.getNewArrival(catalog);
			data.put("" + catalog, list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getOriginList")
	@ResponseBody
	public Object getOriginList() throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<String> list = gDao.getOrigins(0);
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	// 获取目录列表
	@RequestMapping("/getCatalog")
	@ResponseBody
	public Object getCatalog(Integer type) throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		if (type == null)
			type = 0;
		List<GoodsCatalog> list = gDao.getCatalog(type);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 获取出产地对应的商品列表
	@RequestMapping("/getGoodsByOriginAndCatalogId")
	@ResponseBody
	public Object getGoodsByOriginAndCatalogId(Integer start, Integer limit,
			String origin, Long catalogId) throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		Short[] state = { (short) 1 };
		List<VGoodsId> list = gDao.getGoodsByOriginAndCatalogId(start, limit,
				origin, state, catalogId);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 获取目录对应的商品列表
	@RequestMapping("/getCataGoods")
	@ResponseBody
	public Object getCataGoods(Integer start, Integer limit, Long catalogId)
			throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		Short[] state = { (short) 1 };
		List<VGoodsId> list = gDao.getCataGoods(start, limit, catalogId, state);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

}
