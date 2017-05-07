package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.AdminDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Admin;
import org.util.HibernateSessionFactory;
import org.view.VAdmin;
import org.view.VAdminId;

public class AdminDaoImp implements AdminDao {
	public Admin getAdmin(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Admin a where a.username=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, username);
			query.setMaxResults(1);
			Admin a = (Admin) query.uniqueResult();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Admin getAdmin(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Admin a where a.username=? and a.password = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			Admin a = (Admin) query.uniqueResult();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Admin getAdmin(Long admin, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Admin a where a.userid=? and a.password = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, admin);
			query.setParameter(1, password);
			query.setMaxResults(1);
			Admin a = (Admin) query.uniqueResult();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Admin getAdmin(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Admin a where a.id=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			Admin a = (Admin) query.uniqueResult();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VAdminId> getAdmins(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VAdmin";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VAdmin> list = query.list();
			List<VAdminId> list2 = new ArrayList<>();
			for (VAdmin v : list) {
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

	@Override
	public Long saveOrUpdate(Admin admin) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (admin.getId() != null) {
				session.update(admin);
			} else {
				id = (Long) session.save(admin);
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

	@Override
	public boolean delete(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from Admin where id=?";
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

	@Override
	public boolean updatePassword(String password, Long adminId) {
		return false;

	}

}
