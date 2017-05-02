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
			String sql = "from VOrders where v.id.userid=?";
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
			String sql = "update Orders set state=-1 where userid=? and id = ? ";
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

	public Long saveOrUpdate(OrdersDetail ordersDetail) {
		// TODO Auto-generated method stub
		return null;
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
			String sql = "from VOrdersDetails where v.id.orderId=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, orderId);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
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

}
