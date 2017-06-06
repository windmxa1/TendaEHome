package org.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.util.HibernateSessionFactory;
import org.util.MD5;
import org.view.VOrders;
import org.view.VOrdersId;

public class Test04 {
	public static void main(String[] args) {
		System.out.println(MD5.string2MD5("123456"));
	}
}
