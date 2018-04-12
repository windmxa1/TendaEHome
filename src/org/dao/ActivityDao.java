package org.dao;

import java.util.List;

import org.model.Activity;
import org.view.VActivityId;
import org.view.VGoodsId;

public interface ActivityDao {
	/**
	 * 获取活动对应的规则
	 */
	public String getActivityRule(Integer actId);

	/**
	 * 根据商品id获取活动
	 */
	public List<Activity> getList(Long goodsId);

	/**
	 * 根据ID获取活动
	 */
	public VActivityId getById(Integer actId);

	/**
	 * 根据活动id获取赠品列表
	 */
	public List<VGoodsId> getGiftById(Integer actId);

}