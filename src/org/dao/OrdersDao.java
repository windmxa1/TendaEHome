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
	 * 获取单个订单
	 */
	public VOrdersId getOrder(String orderNum);
	/**
	 * 获取订单中商品的图片数组
	 */
	public List<String> getUrlList(Long orderId);
	/**
	 * 获取订单详情列表
	 */
	public List<VOrdersDetailsId> getDetailList(Long orderId, Integer start,
			Integer limit);

	/**
	 * 增加订单
	 */
	public Long saveOrUpdate(Orders orders);

	// public Long saveOrUpdate(OrdersDetail ordersDetail);
	/**
	 * 生成订单
	 */
	public Long generateOrder(Orders orders, List<OrdersDetail> details);

	/**
	 * 取消订单
	 */
	public boolean cancel(Long userid, Long id);

	/**
	 * 根据状态获取订单列表,NULL所有,0取消订单,1未支付，2已支付未发货，3发货未签收，4发货已签收
	 */
	public List<VOrdersId> getListByState(Integer start, Integer limit,
			Integer state);

	/**
	 * 根据状态获取订单总数(如果state为Null，则返回所有的订单总数)
	 */
	public Long getCountByState(Integer state);

	/**
	 * 修改订单状态
	 */
	public boolean updateOrder(Long id, Integer state);

	/**
	 * 修改订单状态
	 * 
	 * @param orderNum
	 * @param state
	 * @return
	 */
	public boolean updateOrder(String orderNum, Integer state);

	/**
	 * 删除订单,仅允许删除被取消的订单或未付款的订单
	 */
	public int deleteOrder(Long id);

	/**
	 * 获取订单总价
	 */
	public Double getTotal(String orderNum);
	/**
	 * 获取订单中商品种数
	 */
	public Long getDetailsCount(Long orderId);
	/**
	 * 修改订单对应的配送员编号
	 */
	public Boolean updateOrdersStaffId(String staffId,String orderNum);

}
