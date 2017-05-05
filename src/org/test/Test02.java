package org.test;

import java.util.Calendar;

import org.dao.OrdersDao;
import org.dao.imp.OrdersDaoImp;

public class Test02 {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(c.DATE, 20);
		System.out.println(c.getTime());
		
		OrdersDao oDao = new OrdersDaoImp();
		System.out.println(oDao.getList(1L, null, null));
	}
}
