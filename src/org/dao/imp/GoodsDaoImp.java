package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.GoodsDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Goods;
import org.model.GoodsCatalog;
import org.util.HibernateSessionFactory;
import org.view.VGoods;
import org.view.VGoodsId;

public class GoodsDaoImp implements GoodsDao {
	public Goods getGoods(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Goods g where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			Goods goods = (Goods) query.uniqueResult();
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}

	}

	public List<VGoodsId> getList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VGoods> vGoods = query.list();
			List<VGoodsId> list = new ArrayList<VGoodsId>();
			for (VGoods v : vGoods) {
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

	public List<VGoodsId> getDiscounts(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.state=1 order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<VGoods> vGoods = query.list();
			List<VGoodsId> list = new ArrayList<VGoodsId>();
			for (VGoods v : vGoods) {
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

	public List<VGoodsId> getNewArrival() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v order by v.id.time desc";
			Query query = session.createQuery(sql);
			// if (start == null) {
			// start = 0;
			// }
			// if (limit == null) {
			// limit = 15;
			// }
			// query.setFirstResult(start);
			// query.setMaxResults(limit);
			query.setMaxResults(10);
			List<VGoods> vGoods = query.list();
			List<VGoodsId> list = new ArrayList<VGoodsId>();
			for (VGoods v : vGoods) {
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

	public List<GoodsCatalog> getCatalog() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from GoodsCatalog";
			Query query = session.createQuery(sql);

			List<GoodsCatalog> goodsCatalogs = query.list();
			return goodsCatalogs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public List<VGoodsId> getCataGoods(Integer start, Integer limit,
			Long catalogId) {

		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.catalogId = ? order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			query.setParameter(0, catalogId);
			List<VGoods> vGoods = query.list();
			List<VGoodsId> list = new ArrayList<VGoodsId>();
			for (VGoods v : vGoods) {
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
	public List<VGoodsId> getGoodsByKey(Integer start, Integer limit, String key) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.catalogId in (select id from GoodsCatalog where catalog like ? ) or v.id.name like ? order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			query.setParameter(0, "%" + key + "%");
			query.setParameter(1, "%" + key + "%");
			List<VGoods> vGoods = query.list();
			List<VGoodsId> list = new ArrayList<VGoodsId>();
			for (VGoods v : vGoods) {
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
	public Long saveOrUpdate(Goods goods) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (goods.getId() != null) {
				session.update(goods);
			} else {
				id = (Long) session.save(goods);
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
			String sql = "delete from Goods where id=?";
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
