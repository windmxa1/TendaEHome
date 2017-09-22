package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.StaffDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Staff;
import org.model.StaffPromotion;
import org.util.HibernateSessionFactory;
import org.view.VGoods;
import org.view.VGoodsId;
import org.view.VStaffPromotion;
import org.view.VStaffPromotionId;

public class StaffDaoImp implements StaffDao {

	@Override
	public Long saveOrUpdate2(StaffPromotion staffPromotion) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Long id = 0L;
			if (staffPromotion.getId() != null) {
				session.update(staffPromotion);
			} else {
				id = (Long) session.save(staffPromotion);
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
	public StaffPromotion getStaffPromotion(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from StaffPromotion where id = ?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			StaffPromotion staffPromotion = (StaffPromotion) query
					.uniqueResult();
			return staffPromotion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public StaffPromotion getStaffPromotion(String address) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from StaffPromotion where address = ?");
			query.setParameter(0, address);
			query.setMaxResults(1);
			StaffPromotion staffPromotion = (StaffPromotion) query
					.uniqueResult();
			return staffPromotion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VStaffPromotionId> getStaffPromotionList(Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from VStaffPromotion v";
			Query query = session.createQuery(sql);
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
			query.setFirstResult(start);
			List<VStaffPromotion> vStaffPromotions = query.list();
			List<VStaffPromotionId> list = new ArrayList<VStaffPromotionId>();
			for (VStaffPromotion v : vStaffPromotions) {
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
	public Long getStaffPromotionCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id.id) from VStaffPromotion v";
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
	public Integer saveOrUpdate(Staff staff) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Integer id = 0;
			if (staff.getId() != null) {
				session.update(staff);
			} else {
				id = (Integer) session.save(staff);
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
	public boolean deleteStaff(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from Staff where id  = ?";
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
	public List<Staff> getStaffList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Staff";
			Query query = session.createQuery(sql);
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
			query.setFirstResult(start);
			List<Staff> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getStaffCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from Staff";
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

}
