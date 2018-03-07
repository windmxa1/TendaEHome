package org.test;

import org.dao.imp.OrdersDaoImp;

public class Test07 {
	public static void main(String[] args) {
		System.out.println(new OrdersDaoImp().getTotal(226L));
	}
}
