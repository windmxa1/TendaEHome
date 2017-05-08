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
			List<UserAddress> list = (List<UserAddress>) query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateDefault(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update from UserAddress set default=0";
			Query query = session.createQuery(sql);
			query.executeUpdate();
			String sql2 = "update from UserAddress set default=1 where id=?";
			Query query2 = session.createQuery(sql2);
			query2.setParameter(0, id);
			query2.executeUpdate();
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
