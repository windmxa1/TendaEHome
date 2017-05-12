package org.test;

import org.dao.AdminDao;
import org.dao.GarouselDao;
import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.AdminDaoImp;
import org.dao.imp.GarouselDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserAddressDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.UserAddress;
import org.util.HibernateSessionFactory;
import org.util.JsonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test02 {
	final static OrdersDao oDao = new OrdersDaoImp();
	final static AdminDao aDao = new AdminDaoImp();
	final static UserDao uDao = new UserDaoImp();
	final static UserAddressDao uaDao = new UserAddressDaoImp();
	final static GoodsDao gDao = new GoodsDaoImp();
	final static GarouselDao G_DAO = new GarouselDaoImp();	
	ObjectMapper mapper = JsonUtils.getMapperInstance();

	public static void main(String[] args) {
//		Calendar c = Calendar.getInstance();
//		c.add(c.DATE, 20);
//		System.out.println(c.getTime());
//		System.out.println(gDao.getGoodsByKey(null, null, "黄瓜"));
//		
//		Garousel garousel = new Garousel("","", 1, "");
//		System.out.println(G_DAO.saveOrUpdate(garousel));
		try {
			UserAddress userAddress=new UserAddress(4L, "", "", "", "先生");
			userAddress.setId(8L);
			
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Long id = 0L;
			if (userAddress.getId() != null) {
				session.update(userAddress);
			} else {
				id = (Long) session.save(userAddress);
			}
			ts.commit();
			System.out.println(id);
			HibernateSessionFactory.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
