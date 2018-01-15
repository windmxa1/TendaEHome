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

	/**
	 * 获取未读消息数目
	 * 
	 * @param staffId
	 */
	public int getUnRead();

	/**
	 * 获取未读消息数目
	 * 
	 * @param staffId
	 */
	public int getUnRead(Integer staffId);

	/**
	 * 获取单人的维修单列表
	 */
	public List<VRepairOrderId> getRepairListByStaffId(Integer staffId,
			Integer status, Integer start, Integer limit);

	/**
	 * 修改维修单状态和为维修人员
	 */
	public boolean updateRepairOrder(Long id, Integer status,
			String handleResult,Integer staffId);
	/**
	 * 修改维修单状态
	 */
	public boolean updateRepairOrder(Long id, Integer status,
			String handleResult);

	/**
	 * 修改订单的维修人员ID 
	 */
	public boolean updateRepairOrder(Integer staffId, Long repairOrderId);

}
