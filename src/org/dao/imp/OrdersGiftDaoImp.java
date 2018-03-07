package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.OrdersGiftDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.model.OrdersGift;
import org.util.HibernateSessionFactory;
import org.view.VOrdersGift;
import org.view.VOrdersGiftId;

public class OrdersGiftDaoImp implements OrdersGiftDao {

	@Override
	public List<VOrdersGiftId> getAllByOrderId(Long orderId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_orders_gift where order_id = ?";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, orderId);
			List<VOrdersGift> list = query.addEntity(VOrdersGift.class).list();
			List<VOrdersGiftId> list2 = new ArrayList<>();
			for (VOrdersGift v : list) {
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
