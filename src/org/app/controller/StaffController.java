package org.app.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.StaffDao;
import org.dao.imp.StaffDaoImp;
import org.model.Staff;
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
	 * 新增负责人账户
	 */
	@RequestMapping("/register1")
	@ResponseBody
	public Object register1(HttpServletRequest request, String username,
			String password, String staffNo) throws Exception {
		sDao = new StaffDaoImp();
		Staff staff = sDao.getUserStaff(staffNo, username);
		if (staff != null) {
			if (!staff.getUsername().equals("")) {
				return ResultUtils.toJson(101, "该员工号已注册", "");
			} else {
				staff.setUsername(username);
				staff.setPassword(password);
				if (sDao.saveOrUpdate(staff) == 0) {
					return ResultUtils.toJson(100, "注册成功", "");
				}
			}
		} else {
			Staff staff2 = new Staff(staffNo, username, password,1);
			if (sDao.saveOrUpdate(staff2) > 0) {
				return ResultUtils.toJson(100, "注册成功", "");
			}
		}
		return ResultUtils.toJson(101, "注册失败，请重试", "");
	}
	/**
	 * 新增员工账户
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Object register(HttpServletRequest request, String username,
			String password, String staffNo) throws Exception {
		sDao = new StaffDaoImp();
		Staff staff = sDao.getUserStaff(staffNo, username);
		if (staff != null) {
			if (!staff.getUsername().equals("")) {
				return ResultUtils.toJson(101, "该员工号已注册", "");
			} else {
				staff.setUsername(username);
				staff.setPassword(password);
				if (sDao.saveOrUpdate(staff) == 0) {
					return ResultUtils.toJson(100, "注册成功", "");
				}
			}
		} else {
			Staff staff2 = new Staff(staffNo, username, password);
			if (sDao.saveOrUpdate(staff2) > 0) {
				return ResultUtils.toJson(100, "注册成功", "");
			}
		}
		return ResultUtils.toJson(101, "注册失败，请重试", "");
	}

	/**
	 * 员工登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String username,
			String password) throws Exception {
		sDao = new StaffDaoImp();
		Staff u = sDao.getStaff(username, password);
		if (u != null) {
			data = new HashMap<String, Object>();
			// 验证通过并生成token,c为过期时间，暂时用不到
			Calendar c = Calendar.getInstance();
			c.add(c.DATE, 14);
			String token = TokenUtils.buildJwt3(TokenUtils.getKey(),
					c.getTime(), u.getId());
			// ObjectMapper mapper = new ObjectMapper();
			data.put("token", token);
			data.put("staff", u);
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
