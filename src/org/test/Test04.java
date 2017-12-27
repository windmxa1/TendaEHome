package org.test;

import java.util.List;

import org.dao.RepairDao;
import org.dao.imp.OrdersDaoImp;
import org.dao.imp.RepairDaoImp;
import org.util.ChangeTime;
import org.util.JsonUtils;
import org.util.MD5;
import org.view.VRepairOrderId;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test04 {
	public static void main(String[] args) throws JsonProcessingException {
		System.out.println(new OrdersDaoImp().getList(233L, null, null, 6, 0).size());
	}
}
