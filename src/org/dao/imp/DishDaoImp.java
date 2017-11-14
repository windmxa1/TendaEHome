package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.DishDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.Dish;
import org.model.DishCatalog;
import org.util.HibernateSessionFactory;
import org.view.VGoods;
import org.view.VGoodsId;

public class DishDaoImp implements DishDao {

	@Override
	public List<DishCatalog> getCatalogs() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from DishCatalog";
			Query query = session.createQuery(sql);

			List<DishCatalog> dishCatalogs = query.list();
			return dishCatalogs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Dish> getDishesByCatalog(Integer catalogId, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Dish where catalogId = ? order by id desc";
			Query query = session.createQuery(sql);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			if (catalogId == null) {
				catalogId = 1;
			}
			query.setFirstResult(start);
			query.setMaxResults(limit);
			query.setParameter(0, catalogId);
			List<Dish> dishes = query.list();
			return dishes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
