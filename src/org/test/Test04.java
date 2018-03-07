package org.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dao.FranchiseeDao;
import org.dao.imp.FranchiseeDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.model.Franchisee;
import org.model.Refund;
import org.util.JsonUtils;
import org.util.RedisUtil;
import org.util.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test04 {
	public static void main(String[] args) throws JsonProcessingException {
		Set<String> set = new HashSet<>();
		set.add("asd");
		set.add("asdd");
		System.out.println(set.toString());
	}
}
