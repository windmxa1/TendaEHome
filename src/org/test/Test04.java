package org.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test04 {
	public static void main(String[] args) {
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date beginOfDate = c.getTime();
		String begin = spf.format(beginOfDate);
		System.out.println(begin);

		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		Date endOfDate = c.getTime();
		String end = spf.format(endOfDate);
		System.out.println(end);
	}
}
