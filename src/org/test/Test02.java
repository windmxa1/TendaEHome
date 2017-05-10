package org.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dao.AdminDao;
import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.AdminDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserAddressDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.util.HibernateSessionFactory;
import org.util.JsonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test02 {
	final static OrdersDao oDao = new OrdersDaoImp();
	final static AdminDao aDao = new AdminDaoImp();
	final static UserDao uDao = new UserDaoImp();
	final static UserAddressDao uaDao = new UserAddressDaoImp();
	final static GoodsDao gDao = new GoodsDaoImp();
	ObjectMapper mapper = JsonUtils.getMapperInstance();

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(c.DATE, 20);
		System.out.println(c.getTime());
		System.out.println(gDao.getGoodsByKey(null, null, "黄瓜"));
		
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
//		ids.add(2L);
		ids.add(3L);
		Session session = HibernateSessionFactory.getSession();
		String sql = "";
		for (int i = 0; i < ids.size(); i++) {
			if (i == 0) {
				sql = "where id=?";
			} else {
				sql = sql + " or id=?";
			}
		}
		Query query = session.createQuery("select count(*) from Goods "+sql);
		for (int i = 0; i < ids.size(); i++) {
			query.setParameter(i, ids.get(i));
		}
		query.setMaxResults(1);
		Long count = (Long) query.uniqueResult();
		System.out.println(count);
		
	}
}
