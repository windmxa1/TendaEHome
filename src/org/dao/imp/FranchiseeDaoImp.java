package org.dao.imp;

import java.util.List;

import org.dao.FranchiseeDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Franchisee;
import org.util.HibernateSessionFactory;

public class FranchiseeDaoImp implements FranchiseeDao {

	@Override
	public Long saveOrUpdate(Franchisee franchisee) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Long id = 0L;
			if (franchisee.getId() == null) {
				id = (Long) session.save(franchisee);
			} else {
				session.update(franchisee);
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
	public Franchisee getFranchisee(String nickName, Integer catalogId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Franchisee where catalogId= ? and nickname=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
			query.setParameter(1, nickName);
			query.setMaxResults(1);
			Franchisee franchisee = (Franchisee) query.uniqueResult();
			return franchisee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<String> getNicknameList(Integer catalogId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select nickname from Franchisee where catalogId= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
			List<String> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Franchisee> getList(Integer catalogId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = " from Franchisee where catalogId= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
			List<Franchisee> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
