package org.test;

import java.util.Calendar;

public class Test02 {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(c.DATE, 20);
		System.out.println(c.getTime());
	}
}
