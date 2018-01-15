package org.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dao.FranchiseeDao;
import org.dao.imp.FranchiseeDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.model.Franchisee;
import org.model.Refund;
import org.util.JsonUtils;
import org.util.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test04 {
	public static void main(String[] args) throws JsonProcessingException {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(calendar.DAY_OF_WEEK));
		
	}
}
