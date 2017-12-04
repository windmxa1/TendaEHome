package org.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dao.OrdersDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.model.Orders;
import org.model.OrdersDetail;
import org.model.Refund;
import org.util.ALIPAY;
import org.util.ChangeTime;
import org.util.HibernateSessionFactory;
import org.util.WXAPI;
import org.view.VOrders;
import org.view.VOrdersDetails;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

public class OrdersDaoImp implements OrdersDao {
	@Override
	public List<VOrdersId> getAfterSaleOrder(Long userid, Integer start,
			Integer limit, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (userid == null) {
				sql = "from VOrders v where v.id.afterSaleState>0 and v.id.type = :type order by v.id.time desc";
				query = session.createQuery(sql);
			} else {
				sql = "from VOrders v where v.id.userid=? and v.id.afterSaleState>0 and v.id.type = :type order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, userid);
			}
			query.setParameter("type", type);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VOrders> vOrders = query.list();
			List<VOrdersId> list = new ArrayList<VOrdersId>();
			for (VOrders v : vOrders) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getAfterSaleCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(v.id.id) from VOrders v where v.id.afterSaleState>0 order by v.id.time desc";
			Query query = session.createQuery(sql);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VOrdersId> getList(Long userid, Integer start, Integer limit,
			Integer state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "from VOrders v where v.id.userid=? and v.id.type = :type order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, userid);
			} else {
				sql = "from VOrders v where v.id.userid=? and v.id.state=? and v.id.type = :type order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, userid);
				query.setParameter(1, state);
			}
			query.setParameter("type", type);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VOrders> vOrders = query.list();
			List<VOrdersId> list = new ArrayList<VOrdersId>();
			for (VOrders v : vOrders) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public VOrdersId getOrder(String orderNum) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VOrders v where v.id.orderNum=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, orderNum);
			query.setMaxResults(1);
			VOrders v = (VOrders) query.uniqueResult();
			return v.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public VOrdersId getOrder(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VOrders v where v.id.id=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			VOrders v = (VOrders) query.uniqueResult();
			return v.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long saveOrUpdate(Orders orders) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (orders.getId() != null) {
				session.update(orders);
			} else {
				id = (Long) session.save(orders);
			}
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int updateRefundId1(Long id, Refund r) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.save(r);
			String sql = "from Orders where id = ? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			Orders o = (Orders) query.uniqueResult();
			Query query2 = session
					.createQuery("select id.total from VOrders where id.id= ?");
			query2.setParameter(0, id);
			Double total = (Double) query2.uniqueResult();
			if (o.getPayWay() > 0) {// 订单已支付
				o.setRefundId(r.getRefundId());
				switch (o.getPayWay()) {
				case 1:// 微信
					Integer total2 = (int) (total * 100);
					if (!WXAPI.doRefund(o.getOrderNum(), r.getRefundId(),
							total2, total2, r.getDescription())) {
						return -1;
					}
					break;
				case 2:// 支付宝
					if (!ALIPAY.doRefund(o.getOrderNum(), r.getRefundId(),
							total, r.getDescription())) {
						return -1;
					}
					break;
				}
				ts.commit();
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int updateRefundId(Long id, Refund r, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.save(r);
			String sql = "from Orders where id = ? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			Orders o = (Orders) query.uniqueResult();
			if (o.getPayWay() > 0) {// 订单已支付
				o.setRefundId(r.getRefundId());
				switch (o.getPayWay()) {
				case 1:// 微信
					Query query2 = session
							.createQuery("select id.total from VOrders where id.id= ?");
					query2.setParameter(0, id);
					Double total = (Double) query2.uniqueResult();
					Integer total2 = (int) (total * 100);
					Integer total3 = 0;
					if (type == 1) {// 后台申请，可以做到多次退款
						total3 = (int) (r.getRefundFee() * 100);
					} else {
						total3 = total2;
					}
					if (!WXAPI.doRefund(o.getOrderNum(), r.getRefundId(),
							total3, total2, r.getDescription())) {
						return -1;
					}
					break;
				case 2:// 支付宝
					if (!ALIPAY.doRefund(o.getOrderNum(), r.getRefundId(),
							r.getRefundFee(), r.getDescription())) {
						return -1;
					}
					break;
				}
				ts.commit();
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int cancel(Long userid, Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "";
			Query query = null;
			if (userid == null) {
				sql = "update Orders set payWay=-1 where id = ? and payWay=0 ";
				query = session.createQuery(sql);
				query.setParameter(0, id);
			} else {
				sql = "update Orders set payWay=-1 where userid=? and id = ? and payWay=0 ";
				query = session.createQuery(sql);
				query.setParameter(0, userid);
				query.setParameter(1, id);
			}
			int a = query.executeUpdate();
			ts.commit();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean cancel() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update orders set pay_way = -1 where pay_way=0 and now()>date_add(from_unixtime(time),interval 1 day) ";
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean finish() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update orders set delivery_state = 2 where delivery_state=1 and now()>date_add(from_unixtime(time),interval 2 day) ";
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long generateOrder(Orders orders, final List<OrdersDetail> details) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			final Long id = (Long) session.save(orders);
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					PreparedStatement stmt = connection
							.prepareStatement("insert into orders_detail(order_id,goods_id,num) values(?,?,?)");
					connection.setAutoCommit(false);
					for (OrdersDetail d : details) {
						stmt.setLong(1, id);
						stmt.setLong(2, d.getGoodsId());
						stmt.setDouble(3, d.getNum());
						stmt.addBatch();
					}
					stmt.executeBatch();
				}
			});
			ts.commit();
			session.flush();
			session.clear();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<String> getUrlList(Long orderId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select vg.id.goodsUrl from VOrdersDetails v,VGoods vg where v.id.orderId=? and v.id.goodsId=vg.id.goodsId ";
			Query query = session.createQuery(sql);
			query.setParameter(0, orderId);
			List<String> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VOrdersDetailsId> getDetailList(Long orderId, Integer start,
			Integer limit, String origin) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (origin == null) {
				sql = "from VOrdersDetails v where v.id.orderId=? ";
				query = session.createQuery(sql);
				query.setParameter(0, orderId);
			} else {
				sql = "from VOrdersDetails v where v.id.orderId=? and origin like ?";
				query = session.createQuery(sql);
				query.setParameter(0, orderId);
				query.setParameter(1, "%" + origin + "%");
			}
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			List<VOrdersDetails> vOrders = query.list();
			List<VOrdersDetailsId> list = new ArrayList<VOrdersDetailsId>();
			for (VOrdersDetails v : vOrders) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VOrdersId> getListByState1(Integer start, Integer limit,
			Integer state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "from VOrders v  where date(v.id.createTime) = curdate() and type= :type order by v.id.time desc";
				query = session.createQuery(sql);
			} else {
				sql = "from VOrders v where v.id.state=? and date(v.id.createTime) = curdate() and type= :type order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, state);
			}
			query.setParameter("type", type);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			List<VOrders> vOrders = query.list();
			List<VOrdersId> list = new ArrayList<VOrdersId>();
			for (VOrders v : vOrders) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VOrdersId> getListByState2(Integer start, Integer limit,
			Integer state, String address, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "from VOrders v  where v.id.isExport=0 and date(v.id.createTime) = curdate() and id.address like ? and type= :type order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, "%" + address + "%");
			} else {
				sql = "from VOrders v where v.id.isExport=0 and v.id.state=? and date(v.id.createTime) = curdate() and id.address like ? and type= :type order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, state);
				query.setParameter(1, "%" + address + "%");
			}
			query.setParameter("type", type);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			List<VOrders> vOrders = query.list();
			List<VOrdersId> list = new ArrayList<VOrdersId>();
			List<Long> ids = new ArrayList<>();
			for (VOrders v : vOrders) {
				list.add(v.getId());
				ids.add(v.getId().getId());
			}
			if (ids.size() > 0) {
				Query query2 = session
						.createQuery("update Orders set isExport=1 where from_unixtime(time,'%Y-%m-%d') = curdate() and address like ? and id in (:idList)");
				query2.setParameter(0, "%" + "生态城" + "%");
				query2.setParameterList("idList", ids);
				query2.executeUpdate();
			}
			ts.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VOrdersId> getListByState(Integer start, Integer limit,
			Integer state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "from VOrders v where v.id.type=:type order by v.id.time desc";
				query = session.createQuery(sql);
			} else {
				sql = "from VOrders v where v.id.state=? and v.id.type=:type order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, state);
			}
			query.setParameter("type", type);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			List<VOrders> vOrders = query.list();
			List<VOrdersId> list = new ArrayList<VOrdersId>();
			for (VOrders v : vOrders) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Double getTotal(String orderNum) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select v.id.total from  VOrders v where v.id.orderNum = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, orderNum);
			query.setMaxResults(1);
			Double total = (Double) query.uniqueResult();
			return total;
		} catch (Exception e) {
			e.printStackTrace();
			return 0.00;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateOrder(String orderNum, Integer payWay) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Orders set payWay=? where orderNum = ? and payWay = 0";// 防止重复修改
			Query query = session.createQuery(sql);
			query.setParameter(0, payWay);
			query.setParameter(1, orderNum);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int deleteOrder(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete o.* from orders o left join refund r on o.id=r.order_id where o.id=? and (o.pay_way=-1 or o.pay_way=0 or o.delivery_state=2 or r.state=1)";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, id);
			int a = query.executeUpdate();
			System.out.println(a);
			if (a > 0) {
				String sql2 = "delete from OrdersDetail where orderId = ?";
				Query query2 = session.createQuery(sql2);
				query2.setParameter(0, id);
				query2.executeUpdate();
				String sql3 = "delete from Refund where orderId = ?";
				Query query3 = session.createQuery(sql3);
				query3.setParameter(0, id);
				System.out.println(query3.executeUpdate());
				String sql4 = "delete from AfterSale where orderId = ?";
				Query query4 = session.createQuery(sql4);
				query4.setParameter(0, id);
				query4.executeUpdate();
			}
			ts.commit();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int delOrder(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from Orders where id=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			int a = query.executeUpdate();
			if (a > 0) {
				String sql2 = "delete from OrdersDetail where orderId = ?";
				Query query2 = session.createQuery(sql2);
				query2.setParameter(0, id);
				query2.executeUpdate();
				String sql3 = "delete from Refund where orderId = ?";
				Query query3 = session.createQuery(sql3);
				query3.setParameter(0, id);
				query3.executeUpdate();
				String sql4 = "delete from AfterSale where orderId = ?";
				Query query4 = session.createQuery(sql4);
				query4.setParameter(0, id);
				query4.executeUpdate();
			}
			ts.commit();
			return a;
		} catch (Exception e) {
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCountByState(Integer state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "select count(id) from Orders where id.type = :type ";
				query = session.createQuery(sql);
			} else {
				sql = "select count(id.id) from VOrders where id.state=? and id.type = :type";
				query = session.createQuery(sql);
				query.setParameter(0, state);
			}
			query.setParameter("type", type);

			Long a = (Long) query.uniqueResult();
			return a;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getDetailsCount(Long orderId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from OrdersDetail where orderId=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, orderId);
			query.setMaxResults(1);
			Long a = (Long) query.uniqueResult();
			return a;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}

	}

	@Override
	public Boolean updateOrdersStaffId(String staffId, String orderNum) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Orders set staffId=?,deliveryState=1  where orderNum=? and delivery_state=0";
			Query query = session.createQuery(sql);
			query.setParameter(0, staffId);
			query.setParameter(1, orderNum);
			int r = query.executeUpdate();
			ts.commit();
			return r > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateDeliveryState(Integer deliveryState, Long userid,
			Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "";
			Query query = null;
			if (userid == null) {
				sql = "update Orders set deliveryState=? where id= ? ";
				query = session.createQuery(sql);
				query.setParameter(0, deliveryState);
				query.setParameter(1, id);
			} else {
				sql = "update Orders set deliveryState=? where id= ? and userid = ? ";// 防止重复修改
				query = session.createQuery(sql);
				query.setParameter(0, deliveryState);
				query.setParameter(1, id);
				query.setParameter(2, userid);
			}
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
