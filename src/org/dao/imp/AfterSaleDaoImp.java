package org.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dao.AfterSaleDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.model.AfterSale;
import org.util.HibernateSessionFactory;

public class AfterSaleDaoImp implements AfterSaleDao {
	@Override
	public boolean addAfterSale(AfterSale afterSale, final List<String> urlList) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			// 增加售后记录
			final String id = (String) session.save(afterSale);
			// 更新售后状态
			String sql = "update Orders set afterSaleState=1 where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.executeUpdate();
			// 添加图片记录
			if (urlList.size() > 0) {
				session.doWork(new Work() {
					public void execute(Connection connection)
							throws SQLException {
						PreparedStatement stmt = connection
								.prepareStatement("insert into after_sale_pic(after_sale_id,url) values(?,?)");
						connection.setAutoCommit(false);
						for (String url : urlList) {
							stmt.setString(1, id);
							stmt.setString(2, url);
							stmt.addBatch();
						}
						stmt.executeBatch();
					}
				});
			}
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
	public AfterSale getAfterSale(String orderNum) {
		try {
			Session session = HibernateSessionFactory.getSession();
			SQLQuery query = session
					.createSQLQuery("select as.* from after_sale as,orders o where o.order_num=? and o.id=as.order_id");
			query.setParameter(0, orderNum);
			query.setMaxResults(1);
			query.addEntity(AfterSale.class);
			AfterSale afterSale = (AfterSale) query.uniqueResult();
			return afterSale;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public AfterSale getAfterSale(Long orderId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from AfterSale where orderId=?");
			query.setParameter(0, orderId);
			query.setMaxResults(1);
			AfterSale afterSale = (AfterSale) query.uniqueResult();
			return afterSale;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateAfterSale(AfterSale afterSale) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.update(afterSale);
			Query query = session
					.createQuery("update Orders set afterSaleState = ? where id = ?");
			query.setParameter(0, afterSale.getState());
			query.setParameter(1, afterSale.getOrderId());
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
