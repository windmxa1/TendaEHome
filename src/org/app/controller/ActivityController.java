package org.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dao.ActivityDao;
import org.dao.GoodsDao;
import org.dao.imp.ActivityDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.model.Activity;
import org.model.OrdersDetail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.ResultUtils;
import org.view.VGoods;
import org.view.VGoodsId;
import com.alipay.api.domain.OrderDetail;

@RestController("/app/ActivityController")
@RequestMapping("/app/activity")
public class ActivityController {
	Map<String, Object> data;
	ActivityDao aDao;
	GoodsDao gDao;

	@RequestMapping("getActivityById")
	public Object getActivityByGoodsId(Long goodsId) {
		aDao = new ActivityDaoImp();
		List<Activity> list = aDao.getList(goodsId);
		return ResultUtils.toJson(100, "", list);
	}

	@RequestMapping("getGiftById")
	public Object getGiftById(Integer actId, String actName) throws Exception {
		aDao = new ActivityDaoImp();
		List<VGoodsId> list = aDao.getGiftById(actId);
		if (list == null) {
			return ResultUtils.toJson(101, "请求失败，请重试", "");
		}
		List<OrdersDetail> list2 = new ArrayList<>();
		for (VGoodsId v : list) {
			OrdersDetail od = new OrdersDetail();
			od.setActId(actId);
			od.setActName(actName);
			od.setGoodsId(v.getGoodsId());
			od.setNum(1);
			od.setPrice(0d);
			od.setName(v.getName());
			od.setPrices(0d);
			od.setGoodsUrl(v.getGoodsUrl());
			od.setTime(v.getTime());
			od.setIsGift(1);
			list2.add(od);
		}
		return ResultUtils.toJson(100, "", list2);
	}
}
