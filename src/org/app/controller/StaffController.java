package org.app.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.StaffDao;
import org.dao.imp.StaffDaoImp;
import org.model.UserStaff;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.util.TokenUtils;

@Controller("/app/StaffController")
@RequestMapping("/app/staff")
public class StaffController {
	Map<String, Object> data;
	StaffDao sDao;

	/**
	 * 新增员工账户
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Object register(HttpServletRequest request, UserStaff userStaff)
			throws Exception {
		sDao = new StaffDaoImp();
		if (sDao.getUserStaff(userStaff.getStaffNo()) != null) {
			return ResultUtils.toJson(101, "该员工号已注册过了", "");
		}
		if (sDao.saveOrUpdateUser(userStaff) > 0) {
			return ResultUtils.toJson(100, "", "");
		}
		return ResultUtils.toJson(101, "操作失败，请重试", "");
	}

	/**
	 * 员工登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String username,
			String password) throws Exception {
		sDao = new StaffDaoImp();
		UserStaff u = sDao.getUserStaff(username, password);
		if (u != null) {
			data = new HashMap<String, Object>();
			// 验证通过并生成token,c为过期时间，暂时用不到
			Calendar c = Calendar.getInstance();
			c.add(c.DATE, 14);
			String token = TokenUtils.buildJwt1(TokenUtils.getKey(),
					c.getTime(), u.getStaffNo());
			// ObjectMapper mapper = new ObjectMapper();
			data.put("token", token);
			return ResultUtils.toJson(100, "登录成功", data);
		} else {
			return ResultUtils.toJson(101, "登录失败", "");
		}
	}

	/**
	 * 员工修改密码
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Object updatePassword(HttpServletRequest request, String username,
			String password) throws Exception {
		sDao = new StaffDaoImp();
		if (sDao.updatePwd(username, password)) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

}
