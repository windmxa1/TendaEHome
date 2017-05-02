package org.dao;

import java.util.List;

import org.model.Orders;
import org.model.OrdersDetail;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

public interface OrdersDao {

	/**
	 * 获取订单列表
	 */
	public List<VOrdersId> getList(Long userid, Integer start, Integer limit);

	/**
	 * 获取订单详情列表
	 */
	public List<VOrdersDetailsId> getDetailList(Long goodsId, Integer start, Integer limit);
	
	/**
	 * 增加订单
	 */
	public Long saveOrUpdate(Orders orders);
	public Long saveOrUpdate(OrdersDetail ordersDetail);
	public boolean generateOrder(Orders orders,List<OrdersDetail> details);

	/**
	 * 取消订单
	 */
	public boolean cancel(Long userid,Long id);
	
}
