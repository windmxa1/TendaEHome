package org.dao.imp;

import java.util.List;

import org.dao.RefundDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Refund;
import org.util.HibernateSessionFactory;

public class RefundDaoImp implements RefundDao {

	@Override
	public boolean updateState(String refundId, Integer state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Refund set state = ? where refundId = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setParameter(1, refundId);
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
	public boolean saveOrUpdate(Refund refund) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (refund.getRefundId() == null) {
				session.save(refund);
			} else {
				session.update(refund);
			}
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
	public boolean delete(String refundId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("delete from Refund where refundId=?");
			query.setParameter(0, refundId);
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
	public List<Refund> getRefundList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Refund getRefund(String refundId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(" from Refund where refundId=?");
			query.setParameter(0, refundId);
			Refund refund = (Refund) query.uniqueResult();
			return refund;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Refund getRefundByOrderNum(String orderNum) {
		try {
			Session session = HibernateSessionFactory.getSession();
			SQLQuery query = session
					.createSQLQuery("select r.* from refund r,orders o where o.order_num=? and o.id=r.order_id");
			query.setParameter(0, orderNum);
			query.addEntity(Refund.class);
			Refund refund = (Refund) query.uniqueResult();
			return refund;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Refund getRefund(Long orderId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(" from Refund where orderId=?");
			query.setParameter(0, orderId);
			Refund refund = (Refund) query.uniqueResult();
			return refund;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
