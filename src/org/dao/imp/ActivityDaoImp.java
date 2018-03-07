package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ActivityDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.model.Activity;
import org.util.HibernateSessionFactory;
import org.view.VActivity;
import org.view.VActivityId;
import org.view.VGoods;
import org.view.VGoodsId;

public class ActivityDaoImp implements ActivityDao {

	@Override
	public String getActivityRule(Integer actId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select name from activity_rule ar,activity a where a.id = ? and a.rule_id = ar.id";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, actId);
			return (String) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Activity> getList(Long goodsId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select a.* from activity a,activity_goods ag where a.id=ag.act_id and ag.goods_id=?";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, goodsId);
			return query.addEntity(Activity.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public VActivityId getById(Integer actId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = " from VActivity a where a.id.id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, actId);
			query.setMaxResults(1);
			VActivityId v = ((VActivity) query.uniqueResult()).getId();
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VGoodsId> getGiftById(Integer actId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = " select v.* from v_goods v,activity_gift ag where ag.act_id=? and ag.goods_id = v.goods_id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, actId);
			List<VGoods> v = query.addEntity(VGoods.class).list();
			List<VGoodsId> list = new ArrayList<>();
			for (VGoods goods : v) {
				list.add(goods.getId());
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
