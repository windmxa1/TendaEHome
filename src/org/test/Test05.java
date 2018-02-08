package org.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dao.ActivityDao;
import org.dao.imp.ActivityDaoImp;
import org.model.OrdersDetail;
import org.util.JsonUtils;
import org.view.VActivityId;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test05 {

	public static void main(String[] args) throws JsonProcessingException {
		OrdersDetail od1 = new OrdersDetail(1L, System.currentTimeMillis());
		OrdersDetail od2 = new OrdersDetail(2L, System.currentTimeMillis());
		OrdersDetail od3 = new OrdersDetail(3L, System.currentTimeMillis());
		OrdersDetail od4 = new OrdersDetail(4L, System.currentTimeMillis());
		OrdersDetail od5 = new OrdersDetail(5L, System.currentTimeMillis());
		OrdersDetail od6 = new OrdersDetail(6L, System.currentTimeMillis());
		List<Map<String, Object>> actList = new ArrayList();
		Map<String, Object> detailMap = new HashMap<>();
		List<OrdersDetail> details1 = new ArrayList<>();
		details1.add(od1);
		details1.add(od2);
		detailMap.put("list", details1);
		detailMap.put("name", "满二十减一");
		detailMap.put("actId", 1);
		actList.add(detailMap);
		detailMap = new HashMap<>();
		List<OrdersDetail> details2 = new ArrayList<>();
		details2.add(od3);
		details2.add(od4);
		details2.add(od5);
		detailMap.put("list", details2);
		detailMap.put("name", "满三十减4");
		detailMap.put("actId", 2);
		actList.add(detailMap);
		detailMap = new HashMap<>();
		List<OrdersDetail> details3 = new ArrayList<>();
		details3.add(od6);
		detailMap.put("list", details3);
		detailMap.put("name", "满六十赠送礼品");
		detailMap.put("actId", 3);
		actList.add(detailMap);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 100);
		result.put("msg", "success");
		result.put("data", actList);
		System.out.println(JsonUtils.getMapperInstance().writeValueAsString(
				result));
		ActivityDao aDao = new ActivityDaoImp();
		Double totalPrice = 0d;
//		for (Entry<Integer, List<OrdersDetail>> entry : detailMap.entrySet()) {
//			// 遍历商品数组，确认活动类型
//			String type = "";
//			VActivityId activity = null;
//			if (entry.getKey() != 0) {
//				activity = aDao.getActivity(entry.getKey());
//				type = activity.getTypeName();
//			}
//			switch (type) {// 判断活动类型
//			case "满减":
//				Double total1 = 0d;
//				for (OrdersDetail od : entry.getValue()) {
//					total1 += od.getPrice();
//				}
//				if (activity.getMinPrice() < total1) {
//					totalPrice = totalPrice + total1 - activity.getNum();
//				}
//				break;
//			case "满赠":
//				Double total2 = 0d;
//				for (OrdersDetail od : entry.getValue()) {
//					if (od.getIsGift() == 0) {
//						total2 += od.getPrice();
//					}
//					totalPrice += total2;
//				}
//				break;
//			case "折扣":
//				Double total3 = 0d;
//				for (OrdersDetail od : entry.getValue()) {
//					if (od.getIsGift() == 0) {
//						total3 += od.getPrice();
//					}
//				}
//				break;
//			case "":
//				Double total4 = 0d;
//				for (OrdersDetail od : entry.getValue()) {
//					if (od.getIsGift() == 0) {
//						total4 += od.getPrice();
//					}
//					totalPrice += total4;
//				}
//				break;
//			}
//		}
	}
}
