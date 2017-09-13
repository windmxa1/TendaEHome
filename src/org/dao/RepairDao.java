package org.dao;

import java.util.List;

import org.model.RepairComment;
import org.model.RepairOrder;
import org.view.VRepairOrderId;

public interface RepairDao {
	/**
	 * 增加报修单
	 */
	public Long saveOrUpdate(RepairOrder repair);

	/**
	 * 增加报修人员评价
	 */
	public Long saveOrUpdate(RepairComment repairComment);

	/**
	 * 删除报修单
	 */
	public boolean deleteRepairOrder(Long[] id);

	/**
	 * 用户查看报修单
	 */
	public List<VRepairOrderId> getList(Integer start, Integer limit,
			Long userid);

	/**
	 * 后台查看报修单
	 */
	public List<VRepairOrderId> getList(Integer start, Integer limit,
			Integer[] status);

	/**
	 * 获取报修单的总数
	 */
	public Long getRepairListCount(Integer[] status);

}
