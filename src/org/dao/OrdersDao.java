package org.dao;

import java.util.List;

import org.model.Orders;
import org.model.OrdersDetail;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

public interface OrdersDao {

	/**
	 * 获取单个用户的订单列表
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
	
	/**
	 * 根据状态获取订单列表,0取消订单,1未支付，2已支付未发货，3发货未签收，4发货已签收
	 */
	public List<VOrdersId> getListByState(Integer start, Integer limit,Integer state);
	/**
	 * 修改订单状态
	 */
	public boolean updateOrder(Long id,Integer state);
}
