package org.dao;

import java.util.List;

import org.model.Orders;
import org.model.OrdersDetail;
import org.model.Refund;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

public interface OrdersDao {

	/**
	 * 获取单个用户的订单列表
	 */
	public List<VOrdersId> getList(Long userid, Integer start, Integer limit,Integer state,Integer type);
	/**
	 * 获取单个用户申请了退款的订单列表
	 */
	public List<VOrdersId> getList(Long userid, Integer start, Integer limit,Integer type);

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
			Integer limit,String origin);

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
	 * 未付款进行取消订单
	 */
	public int cancel(Long userid, Long id);
	/**
	 * 定时取消订单
	 */
	public boolean cancel();
	/**
	 * 定时完成订单
	 */
	public boolean finish();

	/**
	 * 根据状态获取订单列表,NULL所有,0取消订单,1未支付，2已支付未发货，3发货未签收，4发货已签收
	 */
	public List<VOrdersId> getListByState(Integer start, Integer limit,
			Integer state,Integer type);

	/**
	 * 根据状态获取订单总数(如果state为Null，则返回所有的订单总数)
	 */
	public Long getCountByState(Integer state,Integer type);

	/**
	 * 修改订单状态
	 */
	public boolean updateOrder(String orderNum,Integer payWay);

	/**
	 * 删除订单,仅允许删除被取消的订单或已完成的订单
	 */
	public int deleteOrder(Long id);
	/**
	 * 删除订单，用于删除不合法的订单
	 */
	public int delOrder(Long id);

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
	public Boolean updateOrdersStaffNo(String staffNo,String orderNum);
	/**
	 * 获取当天订单列表，用于订单导出
	 */
	public List<VOrdersId> getListByState1(Integer start, Integer limit, Integer state,Integer type);
	/**
	 * 获取当天订单列表，用于订单导出，分区导出
	 */
	public List<VOrdersId> getListByState2(Integer start, Integer limit, Integer state,String address,Integer type);
	/**
	 * 根据ID获取订单
	 */
	public VOrdersId getOrder(Long id);
	/**
	 * 更新退款单编号，同时创建退款单，同时请求API
	 * @param type 类型，0代表APP，1代表后台,后台申请绑定退款单号到售后单上，app申请绑定退款单号到订单上
	 */
	int updateRefundId(Long id, Refund r,Integer type);
	/**
	 * 获取申请了售后处理的订单列表
	 *  @param type 订单类型，0为一米菜园，1为智能家居，2为互助式餐厅
	 */
	List<VOrdersId> getAfterSaleOrder(Long userid, Integer start, Integer limit,Integer type);
	/**
	 * 修改订单运货状态
	 */
	 public boolean updateDeliveryState(Integer deliveryState, Long userid, Long id);
	/**
	 * 后台退款，没有当晚9点的限制
	 */
	public int updateRefundId1(Long id, Refund r);
	/**
	 * 统计售后订单的数量
	 */
	public Long getAfterSaleCount();
	/**
	 * 获取订单列表（已评价和待评价）
	 */
	public List<VOrdersId> getListByIsComment(Long userid, Integer start,
			Integer limit, Integer isComment, Integer type);



}
