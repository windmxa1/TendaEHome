package org.dao;

import java.util.List;

import org.model.Refund;

public interface RefundDao {
	/**
	 * 修改或保存refund
	 */
	public boolean saveOrUpdate(Refund refund);

	/**
	 * 删除退款单
	 */
	public boolean delete(String refundId);

	/**
	 * 查询退款单
	 */
	public List<Refund> getRefundList();
	/**
	 * 获取单个退款单
	 */
	public Refund getRefund(String refundId);
	
}
