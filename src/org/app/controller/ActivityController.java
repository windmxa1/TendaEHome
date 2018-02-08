package org.app.controller;

import java.util.List;
import java.util.Map;

import org.dao.ActivityDao;
import org.dao.imp.ActivityDaoImp;
import org.model.Activity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.ResultUtils;

@RestController("/app/ActivityController")
@RequestMapping("/app/activity")
public class ActivityController {
	Map<String, Object> data;
	ActivityDao aDao;

	@RequestMapping("getActivityById")
	public Object getActivityById(Long goodsId) {
		aDao = new ActivityDaoImp();
		List<Activity> list = aDao.getList(goodsId);
		return ResultUtils.toJson(100, "", list);
	}
}
