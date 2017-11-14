package org.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.DishDao;
import org.dao.imp.DishDaoImp;
import org.model.Dish;
import org.model.DishCatalog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.ResultUtils;

@RestController("/app/DishController")
@RequestMapping("/app/dish")
public class DishController {
	Map<String, Object> data;
	DishDao dDao;

	/**
	 * 餐厅列表
	 */
	@RequestMapping("getCatalog")
	public Object getCatalog(HttpServletRequest request) {
		if (dDao == null) {
			dDao = new DishDaoImp();
		}
		List<DishCatalog> list = dDao.getCatalogs();
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 根据餐厅获取菜单
	 */
	@RequestMapping("getDishes")
	public Object getDishes(HttpServletRequest request, Integer catalogId,
			Integer start, Integer limit) {
		if (dDao == null) {
			dDao = new DishDaoImp();
		}
		List<Dish> list = dDao.getDishesByCatalog(catalogId, start, limit);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

}
