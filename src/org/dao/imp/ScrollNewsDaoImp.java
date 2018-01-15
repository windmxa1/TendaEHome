package org.dao.imp;

import org.dao.ScrollNewsDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.ScrollNews;
import org.util.HibernateSessionFactory;

public class ScrollNewsDaoImp implements ScrollNewsDao {

	@Override
	public ScrollNews getLastNews() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from ScrollNews where usage = 0 order by id desc ";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			return (ScrollNews) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ScrollNews getNews(Integer usage, Integer weekday) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from ScrollNews where usage = ? and weekday=?  ";
			Query query = session.createQuery(sql);
			query.setParameter(0, usage);
			query.setParameter(1, weekday);
			query.setMaxResults(1);
			return (ScrollNews) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
