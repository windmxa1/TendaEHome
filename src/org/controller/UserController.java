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
			Integer limit) throws Exception {
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
			Integer limit, Integer read) throws Exception {
		uFeedbackDao = new UserFeedbackDaoImp();
		List<VUserFeedbackId> list = uFeedbackDao.getList(read, start, limit);
		data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("total", uFeedbackDao.getFeedbackCount());
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/deleteFeedback")
	@ResponseBody
	public Object deleteFeedback(HttpServletRequest request, Integer id)
			throws Exception {
		uFeedbackDao = new UserFeedbackDaoImp();
		if (uFeedbackDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		}
		return ResultUtils.toJson(101, "删除失败", "");
	}

	@RequestMapping("/updateFeedbackState")
	@ResponseBody
	public Object updateFeedbackState(HttpServletRequest request, Integer id)
			throws Exception {
		uFeedbackDao = new UserFeedbackDaoImp();
		if (uFeedbackDao.updateRead(id)) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

	/**
	 * 获取免单用户列表
	 */
	@RequestMapping("/getFreeList")
	@ResponseBody
	public Object getFreeList(HttpServletRequest request, Integer start,
			Integer limit) throws Exception {
		uDao = new UserDaoImp();
		List<VUserId> list = uDao.getFreeList(start, limit);
		data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("total", uDao.getFreeCount());
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getUserByPhone")
	@ResponseBody
	public Object getUserByPhone(HttpServletRequest request, String phone,
			Integer start, Integer limit) throws Exception {
		uDao = new UserDaoImp();
		List<VUserId> list = uDao.getUserByPhone(phone, start, limit);
		data = new HashMap<String, Object>();
		data.put("list", list);
		data.put("total", uDao.getCount(phone));
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/setFree")
	@ResponseBody
	public Object setFree(HttpServletRequest request, Integer isFree,
			Long userid) throws Exception {
		uDao = new UserDaoImp();
		if (uDao.updateFree(isFree, userid)) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

}
