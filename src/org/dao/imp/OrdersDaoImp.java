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

	public boolean cancel(Long userid, Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Orders set state=0 where userid=? and id = ? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
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

	public boolean generateOrder(Orders orders, final List<OrdersDetail> details) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			final Long id = (Long) session.save(orders);
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					PreparedStatement stmt = connection
							.prepareStatement("insert into orders_detail(order_id,goods_id,num,prices) values(?,?,?,?)");
					connection.setAutoCommit(false);
					for (OrdersDetail d : details) {
						System.out.println("1");
						stmt.setLong(1, id);
						stmt.setLong(2, d.getGoodsId());
						stmt.setDouble(3, d.getNum());
						stmt.setDouble(4, d.getPrices());
						stmt.addBatch();
					}
					stmt.executeBatch();
				}
			});

			ts.commit();
			session.flush();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
			query.setMaxResults(limit);
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
	public List<VOrdersId> getListByState(Integer start, Integer limit,
			Integer state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VOrders v where v.id.state=? order by v.id.time desc";
			Query query = session.createQuery(sql);
			if (state == null) {
				state = 2;
			}
			query.setParameter(0, state);
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
	public boolean updateOrder(Long id, Integer state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "";
			switch (state) {// 添加情况判断防止数据混乱
			case 3:
				sql = "update Orders set state=? where id = ? where state=2";
				break;
			case 4:
				sql = "update Orders set state=? where id = ? where state=3";
				break;
			default:
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
	public boolean updateOrder(String orderNum, Integer state) {
		System.out.println("update>>>>>");
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Orders set state=? where orderNum = ? and state = 1";// 防止重复修改
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
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
			String sql = "delete Orders where id=? and state=0";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			int a = query.executeUpdate();
			ts.commit();
			return a;
		} catch (Exception e) {
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
