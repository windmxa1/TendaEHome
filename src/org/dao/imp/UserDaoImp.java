package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.User;
import org.util.HibernateSessionFactory;
import org.view.VUser;
import org.view.VUserId;

public class UserDaoImp implements UserDao {
	public VUserId getUser(String phone){
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VUser v where v.id.phone=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, phone);
			query.setMaxResults(1);
			VUser u = (VUser) query.uniqueResult();
			if(u!=null){
				return u.getId();
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	public VUserId getUser(String phone, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VUser v where v.id.phone=? and v.id.password = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, phone);
			query.setParameter(1, password);
			query.setMaxResults(1);
			VUser u = (VUser) query.uniqueResult();
			if(u!=null){
				return u.getId();
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	public VUserId getUser(Long userid,String password){
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VUser v where v.id.id=? and v.id.password = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setParameter(1, password);
			query.setMaxResults(1);
			VUser u = (VUser) query.uniqueResult();
			if(u!=null){
				return u.getId();
			}else {
				return null;
			}
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

	public boolean updatePassword(String password, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "update from User set password=? where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, password);
			query.setParameter(1, userid);
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

	public boolean updateNickname(String nickname, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "update from User set nickname=? where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, nickname);
			query.setParameter(1, userid);
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

	public boolean updateHead(String url, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "update from User set headUrl=? where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, url);
			query.setParameter(1, userid);
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

	public String getUsedHead(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select headUrl from User where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setMaxResults(1);
			String url = (String) query.uniqueResult();
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VUserId> getUsers(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VUser";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VUser> list = query.list();
			List<VUserId> list2 = new ArrayList<>();
			for (VUser v : list) {
				list2.add(v.getId());
			}
			return list2;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
