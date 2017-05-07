package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.UserDaoImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VUserId;

@Controller
@RequestMapping("/back/user")
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	Map<String, Object> data;

	@RequestMapping("/getUserList")
	@ResponseBody
	public Object getUserList(HttpServletRequest request, Integer start,
			Integer limit) {
		uDao = new UserDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long adminId = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"adminId");
		/*********************************/
		List<VUserId> list = uDao.getUsers(start, limit);
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}
}
