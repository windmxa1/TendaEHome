package org.test;

import java.util.Calendar;

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
import org.model.Admin;

public class Test02 {
	final static OrdersDao oDao = new OrdersDaoImp();
	final static AdminDao aDao = new AdminDaoImp();
	final static UserDao uDao = new UserDaoImp();
	final static UserAddressDao uaDao = new UserAddressDaoImp();
	final static GoodsDao gDao = new GoodsDaoImp();

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(c.DATE, 20);
		System.out.println(c.getTime());
		System.out.println(gDao.getGoodsByKey(null, null, "黄瓜"));

		
	}
}
