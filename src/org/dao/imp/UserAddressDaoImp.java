package org.dao.imp;

import java.util.List;

import org.dao.UserAddressDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.User;
import org.model.UserAddress;
import org.util.HibernateSessionFactory;

public class UserAddressDaoImp implements UserAddressDao {

	public Long saveOrUpdate(UserAddress userAddress) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (userAddress.getId() != null) {
				session.update(userAddress);
			} else {
				id = (Long) session.save(userAddress);
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

	public boolean delete(Long userid, Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from UserAddress where userid=? and id=?";
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

	public List<UserAddress> getList(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from UserAddress where userid= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setMaxResults(1);
			List<UserAddress> list = (List<UserAddress>) query.uniqueResult();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
