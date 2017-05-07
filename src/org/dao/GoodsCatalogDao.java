package org.dao;

import java.util.List;

import org.model.GoodsCatalog;

public interface GoodsCatalogDao {
	public GoodsCatalog getCatalog(String catalog);
	/**
	 * 维护目录信息
	 */
	public Long saveOrUpdate(GoodsCatalog goodsCatalog);

	/**
	 * 删除目录
	 */
	public boolean delete(Long id);

	/**
	 * 获取目录列表
	 */
	public List<GoodsCatalog> getList(Integer start, Integer limit);
}
