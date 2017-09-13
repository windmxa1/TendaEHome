package org.dao;

import java.util.List;

import org.model.StaffPromotion;
import org.view.VStaffPromotionId;

public interface StaffDao {
	/**
	 * 增删或修改APP推广信息
	 */
	public Long saveOrUpdate2(StaffPromotion staffPromotion);
	/**
	 * 获取APP推广信息列表
	 */
	public List<VStaffPromotionId> getStaffPromotionList(Integer start,Integer limit);
	/**
	 * 获取APP推广信息总数
	 */
	public Long getStaffPromotionCount();
	/**
	 * 获取单条推广信息
	 */
	public StaffPromotion getStaffPromotion(Long id);
	/**
	 * 获取单条推广信息
	 */
	public StaffPromotion getStaffPromotion(String address);
}
