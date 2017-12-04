package org.dao;

import java.util.List;

import org.model.Goods;
import org.model.GoodsCatalog;
import org.view.VGoodsId;

public interface GoodsDao {
	/**
	 * 获取商品对象
	 */
	public Goods getGoods(Long id);

	/**
	 * 修改商品上下架状态
	 */
	public boolean updateGoodsState(Long id, Short state);

	/**
	 * 获取商品对象，用于检测商品信息是否被修改
	 */
	public Goods getGoods(Long id, Long time, Short state);

	/**
	 * 获取商品列表，按一定的规则，暂定是销量
	 * 
	 * @return
	 */
	public List<VGoodsId> getList(Integer start, Integer limit, Short[] state,
			Integer type);

	/**
	 * 获取商品总数
	 */
	public Long getCount(Short state, Integer type);

	/**
	 * 获取商品总数
	 */
	public Long getCount(Short[] state, Integer type);

	public Long getCountByCatalog(Long catalogId, Short[] state);

	/**
	 * 获取折扣商品列表
	 */
	public List<VGoodsId> getDiscounts(Integer start, Integer limit,Integer type);

	/**
	 * 最新上架商品列表,按时间选出最新的10种商品
	 */
	public List<VGoodsId> getNewArrival(Integer type);

	/**
	 * 获取目录列表
	 */
	public List<GoodsCatalog> getCatalog(Integer type);

	/**
	 * 获取目录名称列表
	 */
	public List<String> getCatalogs(Integer type);

	/**
	 * 获取目录总数
	 */
	public Long getCatalogCount(Integer type);

	/**
	 * 获取目录对应的商品列表
	 */
	public List<VGoodsId> getCataGoods(Integer start, Integer limit,
			Long catalogId, Short[] state);

	/**
	 * 获取指定名称的商品
	 */
	public List<VGoodsId> getGoodsByKey(Integer start, Integer limit,
			String key, Short[] state,Integer type);

	/**
	 * 获取指定名称的商品总数
	 */
	public Long getCountByKey(String key, Short[] state,Integer type);

	/**
	 * 维护商品信息
	 */
	public Long saveOrUpdate(Goods goods);

	/**
	 * 删除商品,伪删除
	 */
	public boolean delete(Long id);

//	/**
//	 * 获取最新上架的10中商品
//	 */
//	public List<VGoodsId> getNewArrival(String catalog);

	/**
	 * 获取最新上架的10中商品
	 */
	public List<VGoodsId> getNewArrival(Long catalogId);

	/**
	 * 获取目录ID列表
	 */
	public List<Long> getCatalogIds(Integer type);
	/**
	 * 获取商品目录列表
	 */
	public List<GoodsCatalog> getCatalog();
	/**
	 * 获取目录总数
	 */
	public Long getCatalogCount();
	/**
	 * 获取商品
	 */
	public List<VGoodsId> getList(Integer start, Integer limit, Short[] state);
	/**
	 * 获取商品总数
	 */
	public Long getCount(Short[] state);

}