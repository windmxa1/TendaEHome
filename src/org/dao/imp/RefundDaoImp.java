package org.dao.imp;

import java.util.List;

import org.dao.RefundDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Refund;
import org.util.HibernateSessionFactory;

public class RefundDaoImp implements RefundDao {
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
					.createQuery("delete from refund where refundId=?");
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
		// TODO Auto-generated method stub
		return null;
	}

}
