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
			query.setMaxResults(start);
			query.setFirstResult(limit);
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
	public Long saveOrUpdate(GarouselCatalog gCatalog) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (gCatalog.getId() != null) {
				session.update(gCatalog);
			} else {
				id = (Long) session.save(gCatalog);
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
	public boolean delete(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from GarouselCatalog where id=?";
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
