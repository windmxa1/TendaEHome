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
	public VGoodsId getVGoods(Long GoodsId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods where id.id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, GoodsId);
			query.setMaxResults(1);
			VGoods goods = (VGoods) query.uniqueResult();
			VGoodsId goodsId2=goods.getId();
			return goodsId2;
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

	@Override
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

	@Override
	public List<VGoodsId> getList(Integer start, Integer limit, Short[] state,
			Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.state in (:stateList) and type=:type order by v.id.count desc";
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
			query.setParameter("type", type);
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
	public List<VGoodsId> getDiscounts(Integer start, Integer limit,
			Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where (now() between v.id.startDate and v.id.endDate ) and v.id.state=1 and v.id.type=:type order by v.id.count desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			query.setParameter("type", type);
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

	@Override
	public List<VGoodsId> getNewArrival(Long catalogId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.state =1 and v.id.catalogId=? order by v.id.time desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, catalogId);
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

	@Override
	public List<VGoodsId> getNewArrival(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.state =1 and v.id.type = :type order by v.id.time desc";
			Query query = session.createQuery(sql);
			query.setParameter("type", type);
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

	@Override
	public List<GoodsCatalog> getCatalog() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from GoodsCatalog ";
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
	public List<GoodsCatalog> getCatalog(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from GoodsCatalog where type= :type";
			Query query = session.createQuery(sql);
			query.setParameter("type", type);
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
	public List<VGoodsId> getGoodsByOriginAndCatalogId(Integer start,
			Integer limit, String origin, Short[] state, Long catalogId) {

		try {
			Session session = HibernateSessionFactory.getSession();
			StringBuilder sql = new StringBuilder(
					"from VGoods v where  v.id.catalogId=? and v.id.state in (:stateList)");
			Query query = null;
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			if (origin == null || origin.equals("")) {
				sql.append(" order by v.id.count desc");
				query = session.createQuery(sql.toString());
			} else {
				sql.append(" and origin like :origin  order by v.id.count desc");
				query = session.createQuery(sql.toString());
				query.setParameter("origin", "%" + origin + "%");
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
			String key, Short[] state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VGoods v where v.id.name like ? and v.id.state in (:stateList) and v.id.type= :type order by v.id.count desc";
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
			query.setParameter("type", type);
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
	public Long getCount(Short state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from Goods where state=? and type = :type";
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setParameter("type", type);
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
			String sql = "select count(id) from Goods where state in (:stateList) ";
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
	public Long getCount(Short[] state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from Goods where state in (:stateList) and type = :type";
			Query query = session.createQuery(sql);
			query.setParameterList("stateList", state);
			query.setParameter("type", type);
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
	public Long getCountByKey(String key, Short[] state, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(v.id.id) from VGoods v where v.id.name like ? and v.id.state in (:stateList) and v.id.type = :type";
			Query query = session.createQuery(sql);
			query.setParameter(0, state);
			query.setParameterList("stateList", state);
			query.setParameter("type", type);
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
			String sql = "select count(id) from GoodsCatalog ";
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
	public Long getCatalogCount(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from GoodsCatalog where type = :type";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			query.setParameter("type", type);
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

	@Override
	public List<String> getCatalogs(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select catalog from GoodsCatalog where type=:type";
			Query query = session.createQuery(sql);
			query.setParameter("type", type);
			List<String> list = query.list();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Long> getCatalogIds(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select id from GoodsCatalog where type = :type";
			Query query = session.createQuery(sql);
			query.setParameter("type", type);
			List<Long> list = query.list();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<String> getOrigins(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select distinct(origin) from Goods where type=:type and origin !='' ";
			Query query = session.createQuery(sql);
			query.setParameter("type", type);
			List<String> list = query.list();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
