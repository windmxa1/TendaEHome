package org.dao.imp;

import java.util.List;

import org.dao.GoodsCatalogDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.GoodsCatalog;
import org.util.HibernateSessionFactory;

public class GoodsCatalogDaoImp implements GoodsCatalogDao {
	public GoodsCatalog getCatalog(String catalog) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from GoodsCatalog where catalog=?";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			GoodsCatalog goodsCatalog = (GoodsCatalog) query.uniqueResult();
			return goodsCatalog;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Long saveOrUpdate(GoodsCatalog GoodsCatalog) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (GoodsCatalog.getId() != null) {
				session.update(GoodsCatalog);
			} else {
				id = (Long) session.save(GoodsCatalog);
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
			String sql = "delete from GoodsCatalog where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.executeUpdate();

			String sql2 = "update Goods set state = -1 where catalogId=?";
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

	public List<GoodsCatalog> getList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from GoodsCatalog ";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<GoodsCatalog> list = (List<GoodsCatalog>) query.uniqueResult();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
