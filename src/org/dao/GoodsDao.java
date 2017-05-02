package org.dao;

import java.util.List;

import org.model.GoodsCatalog;
import org.view.VGoodsId;

public interface GoodsDao {
	/**
	 * 获取商品列表，按一定的规则，暂定是销量
	 * 
	 * @return
	 */
	public List<VGoodsId> getList(Integer start, Integer limit);

	/**
	 * 获取折扣商品列表
	 */
	public List<VGoodsId> getDiscounts(Integer start, Integer limit);

	/**
	 * 最新上架商品列表,按时间选出最新的10种商品
	 */
	public List<VGoodsId> getNewArrival();

	/**
	 * 获取目录列表
	 */
	public List<GoodsCatalog> getCatalog();

	/**
	 * 获取目录对应的商品列表
	 */
	public List<VGoodsId> getCataGoods(Integer start, Integer limit,Long catalogId);
	/**
	 * 获取指定名称的商品
	 */
	public List<VGoodsId> getGoodsByKey(Integer start, Integer limit,String key);
}
