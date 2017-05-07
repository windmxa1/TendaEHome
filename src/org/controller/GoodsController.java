package org.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.GoodsCatalogDao;
import org.dao.GoodsDao;
import org.dao.imp.GoodsCatalogDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.model.Goods;
import org.model.GoodsCatalog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.view.VGoodsId;

@Controller
@RequestMapping("/back/goods")
public class GoodsController {
	GoodsDao gDao;
	GoodsCatalogDao gcDao;
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

	@RequestMapping("/addCatalog")
	@ResponseBody
	public Object addCatalog(String catalog) {
		gcDao = new GoodsCatalogDaoImp();
		if (gcDao.getCatalog(catalog) != null) {
			return ResultUtils.toJson(101, "添加失败,该目录已存在", "");
		}
		GoodsCatalog goodsCatalog = new GoodsCatalog(catalog);
		if (gcDao.saveOrUpdate(goodsCatalog) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		}
		return ResultUtils.toJson(101, "添加失败", "");
	}

	@RequestMapping("/delCatalog")
	@ResponseBody
	public Object delCatalog(Long id) {
		gcDao = new GoodsCatalogDaoImp();
		if (gcDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		}
		return ResultUtils.toJson(101, "添加失败", "");
	}

	@RequestMapping("/updateCatalog")
	@ResponseBody
	public Object updateCatalog(Long id, String catalog) {
		gcDao = new GoodsCatalogDaoImp();
		GoodsCatalog goodsCatalog = new GoodsCatalog(catalog);
		goodsCatalog.setId(id);
		if (gcDao.saveOrUpdate(goodsCatalog) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

	@RequestMapping("/addGoods")
	@ResponseBody
	public Object addGoods(String name, Double price, String url,
			Long catalogId, String description, Integer state, Double discount,
			String origin) {
		Long time = System.currentTimeMillis() / 1000;
		Goods goods = new Goods(name, price, url, catalogId, time);
		if (description != null) {
			goods.setDescription(description);
		}
		if (origin != null) {
			goods.setOrigin(origin);
		}
		if (gDao.saveOrUpdate(goods) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		}
		return ResultUtils.toJson(101, "添加失败", "");
	}

	@RequestMapping("/deleteGoods")
	@ResponseBody
	public Object deleteGoods(Long id) {
		gDao = new GoodsDaoImp();
		if (gDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		}
		return ResultUtils.toJson(101, "删除失败", "");
	}

	@RequestMapping("/updateGoods")
	@ResponseBody
	public Object updateGoods(Long id, String name, Double price, String url,
			Long catalogId, String description, Integer state, Double discount,
			String origin) {
		gDao = new GoodsDaoImp();
		Long time = System.currentTimeMillis() / 1000;
		Goods goods = new Goods(name, price, url, catalogId, description, time,
				origin);
		goods.setId(id);
		if (gDao.saveOrUpdate(goods) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}
	

}
