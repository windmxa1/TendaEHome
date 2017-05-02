package org.controller;

import java.util.List;
import java.util.Map;

import org.dao.GoodsDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.view.VGoodsId;

@Controller
public class GoodsController {
	GoodsDao gDao;
	Map<String, Object> data;

	// 获取商品信息
	@RequestMapping("/getGoodsList")
	@ResponseBody
	public Object getGoodsList(Integer start, Integer limit) {
		List<VGoodsId> list = gDao.getList(start, limit);
		
		return ResultUtils.toJson(100, "", data);
	}

	// 获取折扣商品信息
	@RequestMapping("/getDiscounts")
	@ResponseBody
	public Object getDiscounts(Integer start, Integer limit) {

		return null;
	}

	// 搜索商品
	@RequestMapping("/searchGoods")
	@ResponseBody
	public Object searchGoods(Integer start, Integer limit) {

		return null;
	}

	// 最新上架
	@RequestMapping("/getNewArrival")
	@ResponseBody
	public Object getNewArrival() {

		return null;
	}

	// 获取目录列表
	@RequestMapping("/getCatalog")
	@ResponseBody
	public Object getCatalog() {

		return null;
	}

	// 获取目录对应的商品列表
	@RequestMapping("/getCataGoods")
	@ResponseBody
	public Object getCataGoods(Integer start, Integer limit) {

		return null;
	}

}
