package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.UserFeedbackDao;
import org.dao.imp.UserDaoImp;
import org.dao.imp.UserFeedbackDaoImp;
import org.model.UserFeedback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VUserFeedbackId;
import org.view.VUserId;

@Controller
@RequestMapping("/back/user")
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	UserFeedbackDao uFeedbackDao;
	Map<String, Object> data;

	@RequestMapping("/getUserList")
	@ResponseBody
	public Object getUserList(HttpServletRequest request, Integer start,
			Integer limit) {
		uDao = new UserDaoImp();
		List<VUserId> list = uDao.getUsers(start, limit);
		data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("total", uDao.getUserCount());
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getFeedbackList")
	@ResponseBody
	public Object getFeedbackList(HttpServletRequest request, Integer start,
			Integer limit, Integer read) {
		uFeedbackDao = new UserFeedbackDaoImp();
		List<VUserFeedbackId> list = uFeedbackDao.getList(read, start, limit);
		data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("total", uFeedbackDao.getFeedbackCount());
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/deleteFeedback")
	@ResponseBody
	public Object deleteFeedback(HttpServletRequest request, Integer id) {
		uFeedbackDao = new UserFeedbackDaoImp();
		if (uFeedbackDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		}
		return ResultUtils.toJson(101, "删除失败", "");
	}

	@RequestMapping("/updateFeedbackState")
	@ResponseBody
	public Object updateFeedbackState(HttpServletRequest request, Integer id) {
		uFeedbackDao = new UserFeedbackDaoImp();
		if (uFeedbackDao.updateRead(id)) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

}
