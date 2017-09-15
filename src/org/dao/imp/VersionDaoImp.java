package org.dao.imp;

import org.dao.VersionDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.AppVersion;
import org.util.HibernateSessionFactory;

public class VersionDaoImp implements VersionDao {

	@Override
	public AppVersion getLastVersion() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from AppVersion order by time desc");
			query.setMaxResults(1);
			AppVersion version = (AppVersion) query.uniqueResult();
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
