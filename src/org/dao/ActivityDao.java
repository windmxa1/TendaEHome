package org.dao;

import java.util.List;

import org.model.Activity;
import org.view.VActivityId;

public interface ActivityDao {
	/**
	 * 获取活动对应的规则
	 */
	public String getActivityRule(Integer actId);
	/**
	 * 根据商品id获取活动
	 */
	public List<Activity> getList(Long goodsId);
	
}
