package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.RepairDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.RepairComment;
import org.model.RepairOrder;
import org.util.HibernateSessionFactory;
import org.view.VRepairOrder;
import org.view.VRepairOrderId;

public class RepairDaoImp implements RepairDao {

	@Override
	public Long saveOrUpdate(RepairOrder repair) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (repair.getId() != null) {
				session.update(repair);
			} else {
				id = (Long) session.save(repair);
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
	public boolean deleteRepairOrder(Long[] id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("delete from RepairOrder where id in (:idList)");
			query.setParameterList("idList", id);
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
	public int getUnRead() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update RepairOrder set isRead=1 where isRead = 0");
			int count = query.executeUpdate();
			ts.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long saveOrUpdate(RepairComment repairComment) {
		Long id = 0L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (repairComment.getId() != null) {
				session.update(repairComment);
			} else {
				id = (Long) session.save(repairComment);
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
	public List<VRepairOrderId> getList(Integer start, Integer limit,
			Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VRepairOrder where id.userid= ? order by id.id desc";
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
			query.setParameter(0, userid);
			List<VRepairOrder> repairOrders = query.list();
			List<VRepairOrderId> list = new ArrayList<>();

			for (VRepairOrder v : repairOrders) {
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
	public List<VRepairOrderId> getList(Integer start, Integer limit,
			Integer[] status) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (status != null && status.length > 0) {
				sql = "from VRepairOrder where id.status in (:typeList) order by id.id desc";
				query = session.createQuery(sql);
				query.setParameterList("typeList", status);
			} else {
				sql = "from VRepairOrder order by id.id desc";
				query = session.createQuery(sql);
			}
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			List<VRepairOrder> repairOrders = query.list();
			List<VRepairOrderId> list = new ArrayList<>();

			for (VRepairOrder v : repairOrders) {
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
	public Long getRepairListCount(Integer[] status) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (status != null && status.length > 0) {
				sql = "select count(id.id) from VRepairOrder where id.status in (:typeList)";
				query = session.createQuery(sql);
				query.setParameterList("typeList", status);
			} else {
				sql = "select count(id.id) from VRepairOrder";
				query = session.createQuery(sql);
			}
			Long count = (Long) query.uniqueResult();

			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
