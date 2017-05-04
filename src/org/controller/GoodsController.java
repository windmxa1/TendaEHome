package org.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.GoodsDao;
import org.dao.imp.GoodsDaoImp;
import org.model.GoodsCatalog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.view.VGoodsId;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	GoodsDao gDao;
	Map<String, Object> data;

	// 获取商品信息
	@RequestMapping("/getGoodsList")
	@ResponseBody
	public Object getGoodsList(Integer start, Integer limit) {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getList(start, limit);
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
		List<VGoodsId> list = gDao.getGoodsByKey(start, limit, key);
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
		List<VGoodsId> list = gDao.getCataGoods(start, limit, catalogId);
		if (list != null) {
			data.put("list", list);
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

}
