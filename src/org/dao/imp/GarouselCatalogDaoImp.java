package org.dao.imp;

import java.util.List;

import org.dao.GarouselCatalogDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.GarouselCatalog;
import org.util.HibernateSessionFactory;

public class GarouselCatalogDaoImp implements GarouselCatalogDao {

	@Override
	public List<GarouselCatalog> getCatalog(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from GarouselCatalog";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setMaxResults(limit);
			query.setFirstResult(start);
			List<GarouselCatalog> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer saveOrUpdate(GarouselCatalog gCatalog) {
		Integer id = 0;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (gCatalog.getId() != null) {
				session.update(gCatalog);
			} else {
				id = (Integer) session.save(gCatalog);
			}
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean delete(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from GarouselCatalog where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.executeUpdate();

			String sql2 = "delete from Garousel where catalogId=?";
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
