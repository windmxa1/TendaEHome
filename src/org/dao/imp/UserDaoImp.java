package org.dao.imp;

import org.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.User;
import org.util.HibernateSessionFactory;

public class UserDaoImp implements UserDao {

	public User getUser(String phone, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from User where phone=? and password = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, phone);
			query.setParameter(1, password);
			query.setMaxResults(1);
			User u = (User) query.uniqueResult();
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Long saveOrUpdate(User user) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (user.getId() != null) {
				session.update(user);
			} else {
				id = (Long) session.save(user);
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

	public boolean delete(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from User where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
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
