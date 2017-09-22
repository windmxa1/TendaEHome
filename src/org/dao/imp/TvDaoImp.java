package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.TvDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Tv;
import org.util.HibernateSessionFactory;
import org.view.VTv;
import org.view.VTvId;

public class TvDaoImp implements TvDao {

	@Override
	public Tv getTv(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from Tv where id = ?");
			query.setParameter(0, id);
			Tv tv = (Tv) query.uniqueResult();
			return tv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VTvId> getTVList1(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VTv";
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

	@Override
	public Long getTVCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from Tv ";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer saveOrUpdate(Tv tv) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Integer id = 0;
			if (tv.getId() == null) {
				id = (Integer) session.save(tv);
			} else {
				session.update(tv);
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
	public Boolean updateTvAvailalbe(Integer id, Integer available) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update Tv set available = ? where id = ?");
			query.setParameter(0, Math.abs(available - 1));
			query.setParameter(1, id);
			Integer r = query.executeUpdate();
			ts.commit();
			if (r > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Boolean deleteTv(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("delete from Tv where id = ?");
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
