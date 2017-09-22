package org.dao;

import java.util.List;

import org.model.Staff;
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
	public List<VStaffPromotionId> getStaffPromotionList(Integer start,
			Integer limit);

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

	/**
	 * 添加或修改员工信息
	 */
	public Integer saveOrUpdate(Staff staff);

	/**
	 * 删除员工信息
	 */
	public boolean deleteStaff(Integer id);

	/**
	 * 获取员工信息列表
	 */
	public List<Staff> getStaffList(Integer start,Integer limit);

	/**
	 * 获取员工总数
	 */
	public Long getStaffCount();
}
