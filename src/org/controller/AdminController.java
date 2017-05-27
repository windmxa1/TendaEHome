package org.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.AdminDao;
import org.dao.imp.AdminDaoImp;
import org.dao.imp.UserDaoImp;
import org.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VAdminId;

@Controller
@RequestMapping("/back/admin")
public class AdminController {
	AdminDao aDao;
	Map<String, Object> data;

	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String username,
			String password) {
		aDao = new AdminDaoImp();		
		Admin a = aDao.getAdmin(username, password);
		if (a != null) {
			data = new HashMap<String, Object>();
			// 验证通过并生成token,c为过期时间，暂时用不到
			Calendar c = Calendar.getInstance();
			c.add(c.DATE, 14);
			String token = TokenUtils.buildJwt2(TokenUtils.getKey(),
					c.getTime(), a.getId());
			a.setPassword("");
			data.put("admin", a);
			data.put("token", token);
			return ResultUtils.toJson(100, "登录成功", data);
		} else {
			return ResultUtils.toJson(101, "登录失败", "");
		}
	}

	@RequestMapping("/register")
	@ResponseBody
	public Object register(String username, String password) {
		aDao = new AdminDaoImp();
		Admin a = aDao.getAdmin(username);
		if (a != null) {
			return ResultUtils.toJson(101, "注册失败，该账号已被注册", "");
		}
		Admin admin = new Admin(username, password,
				System.currentTimeMillis() / 1000);

		Long adminid = aDao.saveOrUpdate(admin);
		if (adminid > 0) {
			return ResultUtils.toJson(100, "注册成功", "");
		}
		return ResultUtils.toJson(101, "注册失败", "");
	}

	@RequestMapping("/updateAdminInfo")
	@ResponseBody
	public Object updateAdminInfo(HttpServletRequest request, String password,
			String newPwd) {
		aDao = new AdminDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long adminId = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"adminId");
		/*********************************/
		if (password != null) {
			if (aDao.getAdmin(adminId, password) == null) {
				return ResultUtils.toJson(101, "修改失败,旧密码不正确", "");
			}
			if (aDao.updatePassword(newPwd, adminId)) {
				return ResultUtils.toJson(100, "修改成功", "");
			}
		}

		return ResultUtils.toJson(101, "修改失败", "");
	}

}
