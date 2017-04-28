package org.controller;

import java.util.Map;

import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.UserInfoDao;
import org.dao.imp.UserDaoImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;

@Controller
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	UserInfoDao uInfoDao;
	Map<String, Object> msg;
	@RequestMapping("/login")
	public @ResponseBody
	Object login(String phone, String password) {
		uDao = new UserDaoImp();
		if(uDao.getUser(phone, password)!=null){
			return ResultUtils.toJson(100, "登录成功", "");
		}else{
			return ResultUtils.toJson(101, "登录失败", "");
		}
			
	}

	@RequestMapping("/login")
	public @ResponseBody
	Object register(String phone, String password) {
		
		return password;
	}

}
