package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.TvDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.Tv;
import org.util.HibernateSessionFactory;
import org.view.VTv;
import org.view.VTvId;

public class TvDaoImp implements TvDao {

	@Override
	public List<VTvId> getTVList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VTv where id.available = 1";
			Query query = session.createQuery(sql);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			List<VTv> list = query.list();
			List<VTvId> list2 = new ArrayList<>();
			for (VTv v : list) {
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
