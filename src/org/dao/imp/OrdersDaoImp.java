package org.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dao.OrdersDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.model.Orders;
import org.model.OrdersDetail;
import org.util.ChangeTime;
import org.util.HibernateSessionFactory;
import org.view.VOrders;
import org.view.VOrdersDetails;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;

public class OrdersDaoImp implements OrdersDao {

	public List<VOrdersId> getList(Long userid, Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VOrders v where v.id.userid=? order by v.id.time desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
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

	public int cancel(Long userid, Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from Orders where userid=? and id = ? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setParameter(1, id);
			Orders o = (Orders) query.uniqueResult();
			if (o.getState() > 1) {
				if (System.currentTimeMillis() / 1000 > ChangeTime
						.hourTimeStamp(21, o.getTime())) {// 晚于下单当天9点且已付款则不更新
					return -2;
				}
				o.setState(5);
				ts.commit();
				return 1;
			} else {
				o.setState(0);
				ts.commit();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

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
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VOrdersDetails v where v.id.orderId=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, orderId);
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
			Integer state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "from VOrders v  where date(v.id.createTime) = curdate() order by v.id.time desc";
				query = session.createQuery(sql);
			} else {
				sql = "from VOrders v where v.id.state=? and date(v.id.createTime) = curdate() order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, state);
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
			Integer state, String address) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "from VOrders v  where v.id.isExport=0 and date(v.id.createTime) = curdate() and id.address like ? order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, "%" + address + "%");
			} else {
				sql = "from VOrders v where v.id.isExport=0 and v.id.state=? and date(v.id.createTime) = curdate() and id.address like ? order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, state);
				query.setParameter(1, "%" + address + "%");
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
			List<VOrders> vOrders = query.list();
			List<VOrdersId> list = new ArrayList<VOrdersId>();
			for (VOrders v : vOrders) {
				list.add(v.getId());
			}
			if (state == null) {
				Query query2 = session
						.createQuery("update Orders set isExport=1 where from_unixtime(time,'%Y-%m-%d') = curdate() and address like ?");
				query2.setParameter(0, "%" + address + "%");
				query2.executeUpdate();
			} else {
				Query query2 = session
						.createQuery("update Orders set isExport=1 where state=? and from_unixtime(time,'%Y-%m-%d') = curdate() and address like ?");
				query2.setParameter(0, state);
				query2.setParameter(1, "%" + address + "%");
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
			Integer state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "from VOrders v order by v.id.time desc";
				query = session.createQuery(sql);
			} else {
				sql = "from VOrders v where v.id.state=?  order by v.id.time desc";
				query = session.createQuery(sql);
				query.setParameter(0, state);
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
	public boolean completeRefund() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Orders set state=6 where state=5";
			Query query = session.createQuery(sql);
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
	public boolean updateOrder(Long id, Integer state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "";
			switch (state) {// 添加情况判断防止数据混乱
			case 4:// 发货->完成
				sql = "update Orders set state=? where id = ? and state=3";
				break;
			case 5:// 已支付->退款
				sql = "update Orders set state=? where id = ? and state=2";
				break;
			case 6:// 退款中->交易关闭
				sql = "update Orders set state=? where id = ? and state=5";
				break;
			default:// 未支付->取消订单
				sql = "update Orders set state=? where id = ? ";
				break;
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setParameter(1, id);
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
	public boolean updateOrder(String orderNum, Integer state, Integer payWay) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Orders set state=?,payWay=? where orderNum = ? and state = 1";// 防止重复修改
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setParameter(1, payWay);
			query.setParameter(2, orderNum);
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
			String sql = "delete Orders where id=? and (state=0 or state=4)";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			int a = query.executeUpdate();
			if (a > 0) {
				String sql2 = "delete OrdersDetail where orderId = ?";
				Query query2 = session.createQuery(sql2);
				query2.setParameter(0, id);
				query2.executeUpdate();
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
	public int delOrder(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete Orders where id=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			int a = query.executeUpdate();
			if (a > 0) {
				String sql2 = "delete OrdersDetail where orderId = ?";
				Query query2 = session.createQuery(sql2);
				query2.setParameter(0, id);
				query2.executeUpdate();
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
	public Long getCountByState(Integer state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (state == null) {
				sql = "select count(id) from Orders ";
				query = session.createQuery(sql);
			} else {
				sql = "select count(id) from Orders where state=?";
				query = session.createQuery(sql);
				query.setParameter(0, state);
			}

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
			String sql = "update Orders set staffId=?,state=3  where orderNum=? and state<3";
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

}
