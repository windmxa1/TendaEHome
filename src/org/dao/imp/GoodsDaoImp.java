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

	public Goods getGoods(Long id, Long time, Short state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Goods g where id=? and time=? and state=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.setParameter(1, time);
			query.setParameter(2, state);
			query.setMaxResults(1);
			Goods goods = (Goods) query.uniqueResult();
			return goods;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public List<VGoodsId> getList(Integer start, Integer limit, Short[] state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.state in (:stateList) order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			query.setParameterList("stateList", state);
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
			String sql = "from VGoods v where (now() between v.id.startDate and v.id.endDate ) and v.id.state=1 order by v.id.count desc";
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
				System.out.println(11);
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
			String sql = "from VGoods v where v.id.state =1 order by v.id.time desc";
			Query query = session.createQuery(sql);
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

	@Override
	public List<VGoodsId> getCataGoods(Integer start, Integer limit,
			Long catalogId, Short[] state) {

		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.catalogId = ? and v.id.state in (:stateList) order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			if (catalogId == null) {
				catalogId = 1L;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			query.setParameter(0, catalogId);
			query.setParameterList("stateList", state);
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
	public List<VGoodsId> getGoodsByKey(Integer start, Integer limit,
			String key, Short state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.state= ? and v.id.name like ? order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			query.setParameter(0, state);
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
			String sql = "update Goods set state= -1 where id = ?";
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
	public Long getCount(Short state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from Goods where state=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setMaxResults(1);
			Long a = (Long) query.uniqueResult();
			return a;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCount(Short[] state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from Goods where state in (:stateList)";
			Query query = session.createQuery(sql);
			query.setParameterList("stateList", state);
			query.setMaxResults(1);
			Long a = (Long) query.uniqueResult();
			return a;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCountByCatalog(Long catalogId, Short[] state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from Goods where catalogId=? and state in (:stateList)";
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
			query.setParameterList("stateList", state);
			query.setMaxResults(1);
			Long a = (Long) query.uniqueResult();
			return a;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCountByKey(String key, Short state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(v.id.id) from VGoods v where v.id.state =? and v.id.catalogId in (select id from GoodsCatalog where catalog like ? ) or v.id.name like ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setParameter(1, "%" + key + "%");
			query.setParameter(2, "%" + key + "%");
			query.setMaxResults(1);
			Long a = (Long) query.uniqueResult();
			return a;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCatalogCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from GoodsCatalog";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			Long a = (Long) query.uniqueResult();
			return a;
		} catch (Exception e) {
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateGoodsState(Long id, Short state) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update Goods set state=? where id =? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setParameter(1, id);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
