package org.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.model.Franchisee;
import org.util.JsonUtils;
import org.util.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test03 {
	public static void main(String[] args) throws JsonProcessingException {
		ArrayList cs = new ArrayList();
		Franchisee c1 = new Franchisee();
		c1.setNickname("客舱经理");
		Franchisee c2 = new Franchisee();
		c2.setNickname("乘务长");
		Franchisee c3 = new Franchisee();
		c3.setNickname("头等舱乘务员");
		Franchisee c4 = new Franchisee();
		c4.setNickname("见习乘务长");
		Franchisee c5 = new Franchisee();
		c5.setNickname("乘务员");
		Franchisee c6 = new Franchisee();
		c6.setNickname("乘务学员");
		cs.add(c2);
		cs.add(c1);
		cs.add(c4);
		cs.add(c3);
		cs.add(c6);
		cs.add(c5);
		List ss = Arrays
				.asList("乘务学员", "客舱经理", "头等舱乘务员", "见习乘务长","乘务长",  "乘务员");
		final List orders = ss;
		Comparator comparator = new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (orders.indexOf(((Franchisee) o1).getNickname()) < orders
						.indexOf(((Franchisee) o2).getNickname())) {
					return -1;
				} else
					return 1;
			}
		};
		Collections.sort(cs, comparator);
		System.out
				.println(JsonUtils.getMapperInstance().writeValueAsString(cs));
	}
}
