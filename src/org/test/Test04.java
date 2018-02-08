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
import org.util.RedisUtil;
import org.util.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test04 {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"command_code\":1000,\"description\":\"heartbeat\",\"seq_num\":\"xxxx\"}";
		System.out.println(json.getBytes().length);
	}
}
