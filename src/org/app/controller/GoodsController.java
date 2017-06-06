package org.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bean.OrderModel;
import org.dao.GoodsDao;
import org.dao.imp.GoodsDaoImp;
import org.model.GoodsCatalog;
import org.model.OrdersDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.view.VGoodsId;

@Controller("/app/GoodsController")
@RequestMapping("/app/goods")
public class GoodsController {
	GoodsDao gDao;
	Map<String, Object> data;

	// 获取商品信息
	@RequestMapping("/getGoodsList")
	@ResponseBody
	public Object getGoodsList(Integer start, Integer limit) {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getList(start, limit, (short) 1);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/checkGoods")
	@ResponseBody
	public Object checkGoods(@RequestBody OrderModel o) {
		StringBuffer list = new StringBuffer();
		gDao = new GoodsDaoImp();
		for (OrdersDetail od : o.getDetails()) {
			if (gDao.getGoods(od.getGoodsId(), od.getTime(), (short) 1) == null) {
				if (list.length() == 0) {
					list.append(od.getName());
				} else {
					list.append("、" + od.getName());
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
	public Object getGoodsList() {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getList(0, 10, (short) 1);
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
	public Object getDiscounts(Integer start, Integer limit) {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getDiscounts(start, limit);
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
	public Object searchGoods(Integer start, Integer limit, String key) {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getGoodsByKey(start, limit, key, (short) 1);
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
	public Object getNewArrival() {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getNewArrival();
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 获取目录列表
	@RequestMapping("/getCatalog")
	@ResponseBody
	public Object getCatalog() {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<GoodsCatalog> list = gDao.getCatalog();
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
	public Object getCataGoods(Integer start, Integer limit, Long catalogId) {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getCataGoods(start, limit, catalogId,
				(short) 1);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

}
