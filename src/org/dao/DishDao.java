package org.dao;

import java.util.List;

import org.model.Dish;
import org.model.DishCatalog;

public interface DishDao {
	/**
	 * 获取菜品目录列表
	 */
	List<DishCatalog> getCatalogs();
	/**
	 * 根据目录ID获取菜品
	 */
	List<Dish> getDishesByCatalog(Integer catalogId,Integer start,Integer limit);
}
