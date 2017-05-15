package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.GarouselDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Garousel;
import org.util.HibernateSessionFactory;
import org.view.VGarousel;
import org.view.VGarouselId;

public class GarouselDaoImp implements GarouselDao {
	@Override
	public VGarouselId getGarousel(Long id){
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGarousel v where v.id.id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			VGarousel garousel = (VGarousel) query.uniqueResult();
			return garousel.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	@Override
	public List<VGarouselId> getList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGarousel v order by v.id.catalogId";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VGarousel> vGarousel = query.list();
			List<VGarouselId> list = new ArrayList<VGarouselId>();
			for (VGarousel v : vGarousel) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long saveOrUpdate(Garousel garousel) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (garousel.getId() != null) {
				session.update(garousel);
			} else {
				id = (Long) session.save(garousel);
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
			String sql = "delete from Garousel where id=?";
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
	public List<VGarouselId> getListByCatalog(Integer catalogId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGarousel v where v.id.catalogId=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
			List<VGarousel> vGarousel = query.list();
			List<VGarouselId> list = new ArrayList<VGarouselId>();
			for (VGarousel v : vGarousel) {
				list.add(v.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
