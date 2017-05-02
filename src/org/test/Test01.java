package org.test;

import java.util.ArrayList;
import java.util.List;

import org.dao.OrdersDao;
import org.dao.UserDao;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Orders;
import org.model.OrdersDetail;
import org.model.User;
import org.util.HibernateSessionFactory;

public class Test01 {
	public static void main(String[] args) {
		UserDao uDao = new UserDaoImp();
		OrdersDao oDao = new OrdersDaoImp();
		Orders order = new Orders(1L, System.currentTimeMillis()/1000, 0, 1L);
		OrdersDetail detail1 = new OrdersDetail();
		detail1.setNum(1d);
		detail1.setPrices(1d);
		detail1.setGoodsId(1L);
		OrdersDetail detail2 = new OrdersDetail();
		detail2.setNum(1d);
		detail2.setPrices(1d);
		detail2.setGoodsId(2L);
		
		
		List<OrdersDetail> details = new ArrayList<OrdersDetail>();
		details.add(detail1);
		details.add(detail2);
		oDao.generateOrder(order, details);
	}
}
